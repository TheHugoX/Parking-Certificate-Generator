package com.obsbs.utils.distance;

import com.obsbs.management.beans.DataBean;
import com.obsbs.ui.panels.EntryConfigurationPanel;

public class DistanceUtil {
    private static final DistanceManager DISTANCE_MANAGER = new DistanceManager();

    public static double getDistance(DataBean dataBean) {
        if (dataBean == null) {
            return 0;
        }

        String city = dataBean.getValue(EntryConfigurationPanel.Field.CITY.getKey());
        String street = dataBean.getValue(EntryConfigurationPanel.Field.STREET.getKey());
        String companyCity = dataBean.getValue(EntryConfigurationPanel.Field.COMPANY_CITY.getKey()); //ToDo school city
        String companyStreet = dataBean.getValue(EntryConfigurationPanel.Field.COMPANY_STREET.getKey()); //ToDo school street

        if (city == null || street == null || companyCity == null || companyStreet == null) {
            return 0;
        }

        Double distance = DISTANCE_MANAGER.GetDistance(city, street, companyCity, companyStreet);
        return distance == null ? 0 : distance;
    }
}
