package com.ontravel.controller;

import java.util.function.Supplier;

public class NosuchCityException extends Exception implements Supplier< Exception> {
    public NosuchCityException() {
        super();
    }

    @Override
    public Exception get() {
        return new  NosuchCityException();
    }


}
