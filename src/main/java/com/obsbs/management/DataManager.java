package com.obsbs.management;

import com.obsbs.management.beans.DataBean;

import java.util.*;

public class DataManager {
    private final static ImportExportManager IMPORT_EXPORT_MANAGER = new ImportExportManager();
    private final Set<DataBean> dataBeans = new HashSet<>();
    private final Set<SafeCommand> terminationCommands = new HashSet<>();

    public DataManager() {
        dataBeans.addAll(IMPORT_EXPORT_MANAGER.load());
    }

    public DataBean add(DataBean dataBean) {
        if (dataBean == null) {
            return null;
        }
        terminateAllJobs();
        dataBeans.add(dataBean);
        IMPORT_EXPORT_MANAGER.export(dataBeans);
        return dataBean;
    }

    public boolean remove(DataBean dataBean) {
        if (dataBean == null) {
            return false;
        }
        terminateAllJobs();
        dataBeans.remove(dataBean);
        IMPORT_EXPORT_MANAGER.export(dataBeans);
        return true;
    }

    public Set<DataBean> findAll() {
        return Collections.unmodifiableSet(dataBeans);
    }

    public void findAllSorted(AsyncCallback<List<DataBean>> asyncCallback, String... keys) {
        sort(asyncCallback, dataBeans, keys);
    }

    public SafeCommand find(final AsyncCallback<Set<DataBean>> asyncCallback, final String searchterm, final String... keys) {
        final SafeCommand terminationCommand = new SafeCommand();
        terminationCommands.add(terminationCommand);

        new Thread(() -> {
            Set<DataBean> result = new HashSet<>();

            final SafeContainer<Boolean> isTerminated = new SafeContainer<>(false);
            terminationCommand.setObject(() -> {
                isTerminated.setObject(true);
                terminationCommands.remove(terminationCommand);
            });

            for (DataBean dataBean : dataBeans) {
                for (String key : keys) {
                    String valueForKey = dataBean.getValue(key);
                    if (valueForKey != null && valueForKey.contains(searchterm)) {
                        result.add(dataBean);
                    }
                }
                if (isTerminated.getObject()) {
                    break;
                }
            }

            asyncCallback.onCallback(result);
        }).run();

        return terminationCommand;
    }

    public void findSorted(final AsyncCallback<List<DataBean>> asyncCallback, String searchterm, final String... keys) {
        find(value -> sort(asyncCallback, value, keys), searchterm, keys);
    }

    public void terminateAllJobs() {
        terminationCommands.forEach(SafeCommand::execute);
    }

    public void export() {
        IMPORT_EXPORT_MANAGER.export(dataBeans);
    }

    public static void sort(final AsyncCallback<List<DataBean>> asyncCallback, final Collection<DataBean> dataBeans, final String... keys) {
        new Thread(() -> {
            List<DataBean> sortedDataBeans = new ArrayList<>(dataBeans);

            Collections.sort(sortedDataBeans, (dataBean0, dataBean1) -> {
                int result = 0;

                for (String key : keys) {
                    String value0 = dataBean0.getValue(key);
                    String value1 = dataBean1.getValue(key);
                    if (value0 != null && value1 != null) {
                        result = value0.compareTo(value1);
                        if (result != 0) {
                            break;
                        }
                    } else if (value0 != null) {
                        return 1;
                    } else if (value1 != null) {
                        return -1;
                    }
                }

                return result;
            });

            asyncCallback.onCallback(Collections.unmodifiableList(sortedDataBeans));
        }).run();
    }

    public interface AsyncCallback<V> {
        void onCallback(V value);
    }

    public interface Command {
        void execute();
    }

    public class SafeCommand extends SafeContainer<Command> implements Command {
        private boolean execute = false;

        @Override
        public void setObject(Command object) {
            super.setObject(object);
            if (execute) {
                getObject().execute();
            }
        }

        @Override
        public void execute() {
            if (getObject() == null) {
                execute = true;
            } else {
                getObject().execute();
            }
        }
    }

    public class SafeContainer<O> {
        private O object;

        public SafeContainer() {
        }

        public SafeContainer(O object) {
            this.object = object;
        }

        public O getObject() {
            return object;
        }

        public void setObject(O object) {
            this.object = object;
        }
    }
}