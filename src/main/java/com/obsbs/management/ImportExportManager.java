package com.obsbs.management;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.obsbs.management.beans.DataBean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;

public class ImportExportManager {
    private static final Gson GSON = new Gson();
    private static final File DATA_FILE = new File("data.json");
    private static final Charset CHARSET = Charset.forName("UTF-8");

    public ImportExportManager() {
    }

    public void export(Collection<DataBean> dataBeans) {
        try {
            Files.write(GSON.toJson(new ExportBean(dataBeans)), DATA_FILE, CHARSET);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<DataBean> load() {
        Collection<DataBean> dataBeans = new HashSet<>();
        if (DATA_FILE.exists()) {
            try {
                dataBeans.addAll(GSON.fromJson(Files.toString(DATA_FILE, CHARSET), ExportBean.class).getDataBeans());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataBeans;
    }

    public static class ExportBean implements Serializable {
        private Collection<DataBean> dataBeans;

        public ExportBean() {
        }

        public ExportBean(Collection<DataBean> dataBeans) {
            this.dataBeans = dataBeans;
        }

        public Collection<DataBean> getDataBeans() {
            return dataBeans;
        }
    }
}
