package com.test.eguay.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Service
public class UtilsService<T>{
    public UtilsService(){}

    public void loadListToRequest(HttpServletRequest request, AbstractFacade facade, String label){
        request.setAttribute(label, facade.findAll());
    }

    public static Integer getId(HttpServletRequest request, String label){
        String idStrg = request.getParameter(label);
        return idStrg==null ? null : Integer.valueOf(idStrg);
    }

    public static Long getIdLong(HttpServletRequest request, String label){
        return Long.valueOf(request.getParameter(label));
    }

    public static List<Long> getIdsFromCheckedLong(HttpServletRequest request, String label) {
        String[] checkedList = request.getParameterValues(label);
        List<Long> ids = null;

        if(checkedList != null){
            ids = new LinkedList<>();
            for(String id : checkedList){
                ids.add(Long.valueOf(id));
            }
        }

        return ids;
    }

    public static List<Integer> getIdsFromChecked(HttpServletRequest request, String label) {
        String[] checkedList = request.getParameterValues(label);
        List<Integer> ids = null;

        if(checkedList != null){
            ids = new LinkedList<>();
            for(String id : checkedList){
                ids.add(Integer.valueOf(id));
            }
        }

        return ids;
    }

    public List<T> getObjectsFromIds(List<Integer> ids, AbstractFacade facade) {
        List<T> objects = null;

        if(ids != null && !ids.isEmpty()){
            objects = new LinkedList<>();

            for(Integer id : ids){
                objects.add((T) facade.find(id));
            }
        }

        return objects;
    }

    public List<T> getObjectsFromIdsLong(List<Long> ids, AbstractFacade facade) {
        List<T> objects = new LinkedList<>();

        for(Long id : ids){
            objects.add((T) facade.find(id));
        }

        return objects;
    }
}
