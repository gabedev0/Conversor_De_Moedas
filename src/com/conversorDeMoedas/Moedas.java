package com.conversorDeMoedas;

public record Moedas
        (double ARS, double BOB, double BRL, double CLP, double COP, double USD) {


    public double getRateFor(String currency) throws NoSuchFieldException, IllegalAccessException {
        return this.getClass().getDeclaredField(currency).getDouble(this);
    }
}