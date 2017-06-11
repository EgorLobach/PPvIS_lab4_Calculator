package controller;

import model.DataBase;

/**
 * Created by anonymous on 05.06.2017.
 */
public class Controller {
    DataBase dataBase;
    public Controller(DataBase dataBase) {
        this.dataBase = dataBase;
    }
    public String startCalc(String inputString){
        return String.valueOf(dataBase.eval(inputString));
    }
}
