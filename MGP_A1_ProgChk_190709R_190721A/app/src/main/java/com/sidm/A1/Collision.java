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

    //xPos and yPos - self explanatory
    //width -  the width of the sprite
    //height - the height of the sprite
    public static boolean AABBv2(float xPos1, float yPos1, float xPos2, float yPos2,float width1, float height1,float width2,float height2)
    {
        //dimensions are halved to enable aabb
        width1 = width1 /2;
        width2 = width2 /2;
        height1 = height1/2;
        height2 = height2/2;
        if(xPos1 - width1 < xPos2 + width2 && xPos1 + width1 > xPos2 - width2 && yPos1 - height1 < yPos2+height2 && yPos1 + height1 > yPos2-height2)
        {
            return true;
        }
       return false;
    }
}
