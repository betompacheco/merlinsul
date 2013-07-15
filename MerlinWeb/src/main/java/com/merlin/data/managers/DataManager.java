package com.merlin.data.managers;

import java.util.List;

public interface DataManager {

    public boolean update(Object bean);

    public boolean insert(Object bean);

    public List select(List filtros);

    public boolean delete(Object bean);

    public Object getNewBean();
}
