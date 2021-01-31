package com.sidm.A1;

// Created by TanSiewLan2020
//todo: get width and height
public interface Collidable {
    String GetType();

    float GetPosX();
    float GetPosY();
    float GetRadius();
    float GetWidth();
    float GetHeight();

    void OnHit(Collidable _other);
}

