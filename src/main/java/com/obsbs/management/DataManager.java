package com.obsbs.management;

import com.obsbs.management.beans.DataBean;

import java.util.*;

public class DataManager {
    private final Set<DataBean> dataBeans = new HashSet<>();
    private final Set<SafeCommand> terminationCommands = new HashSet<>();

    public DataManager() {
    }

    public DataManager(Set<DataBean> dataBeans) {
        this.dataBeans.addAll(dataBeans);
    }

    public boolean add(DataBean dataBean) {
        if (dataBean == null) {
            return false;
        }
        terminateAllJobs();
        dataBeans.add(dataBean);
        return true;
    }

    public boolean remove(DataBean dataBean) {
        if (dataBean == null) {
            return false;
        }
        terminateAllJobs();
        dataBeans.remove(dataBean);
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<DataBean> result = new HashSet<>();

                final SafeContainer<Boolean> isTerminated = new SafeContainer<>(false);
                terminationCommand.setObject(new Command() {
                    @Override
                    public void execute() {
                        isTerminated.setObject(true);
                        terminationCommands.remove(terminationCommand);
                    }
                });

                for (DataBean dataBean : dataBeans) {
                    for (String key : keys) {
                        String valueForKey = dataBean.getValueAsString(key);
                        if (valueForKey != null && valueForKey.contains(searchterm)) {
                            result.add(dataBean);
                        }
                    }
                    if (isTerminated.getObject()) {
                        break;
                    }
                }

                asyncCallback.onCallback(result);
            }
        }).run();

        return terminationCommand;
    }

    public void findSorted(final AsyncCallback<List<DataBean>> asyncCallback, String searchterm, final String... keys) {
        find(new AsyncCallback<Set<DataBean>>() {
            @Override
            public void onCallback(Set<DataBean> value) {
                sort(asyncCallback, value, keys);
            }
        }, searchterm, keys);
    }

    public void terminateAllJobs() {
        for (SafeCommand terminationCommand : terminationCommands) {
            terminationCommand.execute();
        }
    }

    public static void sort(final AsyncCallback<List<DataBean>> asyncCallback, final Collection<DataBean> dataBeans, final String... keys) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<DataBean> sortedDataBeans = new ArrayList<>(dataBeans);

                Collections.sort(sortedDataBeans, new Comparator<DataBean>() {
                    @Override
                    public int compare(DataBean dataBean0, DataBean dataBean1) {
                        int result = 0;

                        for (String key : keys) {
                            String value0 = dataBean0.getValueAsString(key);
                            String value1 = dataBean1.getValueAsString(key);
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
                    }
                });

                asyncCallback.onCallback(Collections.unmodifiableList(sortedDataBeans));
            }
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