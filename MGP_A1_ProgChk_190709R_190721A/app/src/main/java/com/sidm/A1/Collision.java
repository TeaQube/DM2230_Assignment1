package com.sidm.A1;

// Created by TanSiewLan2020

public class Collision {

    public static boolean SphereToSphere(float x1, float y1, float radius1, float x2, float y2, float radius2)
    {
        float xVec = x2 - x1;
        float yVec = y2 - y1;

        float distSquared = xVec * xVec + yVec * yVec;

        float rSquared = radius1 + radius2;
        rSquared *= rSquared;

        if (distSquared > rSquared)
            return false;

        return true;
    }

    //x and y are the bottom left coords, width and height are the dimensions of the picture
    public static boolean AABB(float x1, float x2, float y1, float y2 ,float width1, float height1,float width2,float height2)
    {
        if(x1 < x2 + width2 && x1 + width1 > x2 && y1 < y2 + height2 && y1 + height1 > y2)
        {
            return true;
        }
        return false;
    }


}
