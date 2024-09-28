package com.zimworx.odoo_bill_integration.utils;

import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
public class Helpers {

    public String getStringValue(Object value) {
        if (value instanceof Boolean && !((Boolean) value)) {
            return "";
        } else if (value != null) {
            return value.toString();
        } else {
            return "";
        }
    }

    public String convertMany2oneName(Object object) {
        if (object instanceof Object[]) {
            Object[] stateArray = (Object[]) object;
            if (stateArray.length > 1) {
                return (String) stateArray[1];
            }
        }
        return "";
    }

    public Integer convertMany2oneID(Object object) {
        if (object instanceof Object[]) {
            Object[] stateArray = (Object[]) object;
            return (Integer) stateArray[0];
        }

        return -1;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer> convertOneToMany(Object value) {
        ArrayList<Integer> resultList = new ArrayList<>();

        if (value instanceof Object[]) {
            Object[] stateArray = (Object[]) value;
            for (Object item : stateArray) {
                if (item instanceof Integer) {
                    resultList.add((Integer) item);
                }
            }
        }
        return resultList;
    }
}
