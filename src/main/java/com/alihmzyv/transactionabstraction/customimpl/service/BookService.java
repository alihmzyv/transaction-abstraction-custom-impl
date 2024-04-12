package com.alihmzyv.transactionabstraction.customimpl.service;

public interface BookService {

    void saveBookAndThrowUncheckedException(String title);

    void saveBookAndThrowCheckedException(String title) throws Exception;

    void saveBookModeAspectJ(String title);

    void callSaveBookModeAspectJ(String title);

}
