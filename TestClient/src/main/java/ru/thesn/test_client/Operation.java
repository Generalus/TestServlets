package ru.thesn.test_client;


public enum Operation {
    REGISTER_CUSTOMER, GET_BALANCE, SET_BALANCE, MULTI_REQUEST, EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) throws IllegalArgumentException{
        switch(i){
            case 1: return REGISTER_CUSTOMER;
            case 2: return GET_BALANCE;
            case 3: return SET_BALANCE;
            case 4: return MULTI_REQUEST;
            case 5: return EXIT;
            default: throw new IllegalArgumentException();
        }
    }
}
