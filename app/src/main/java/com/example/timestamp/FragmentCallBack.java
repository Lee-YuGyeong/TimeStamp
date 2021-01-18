package com.example.timestamp;

public interface FragmentCallBack {

    public void TimeStyleButtonSelected(String command, int data);

 //   public void TimeColorButtonSelected2(String command, int data, String i, boolean state);

    public void TimeColorButtonSelected(String command, String i);

    public void TimeColorPaletteSelected(String command, int data, String i);

    public void TimeFontButtonSelected(String command, int data);

    public void BorderButtonSelected(String command);


}
