package dev.nymnux.simplepaperdoll.config;

public class CfgPaperDoll {

    public static double X_PERCENT = 0.75;
    public static double Y_PERCENT = 0.9;
    public static int D_X = 64;
    public static int D_Y = 96;
    public static double OFFSET_Y = 1.0;
    public static double SCALE = 64.0;
    public static double CAMERA_ROTATION_H_DEG = -10.0;
    public static double CAMERA_ROTATION_V_DEG = -5.0;
    public static double ROTATE_LIMIT_H_DEG = 60.0;
    public static double ROTATE_LIMIT_V_DEG = 30.0;
    public static double YAW_CHANGE_SPEED = 0.5;
    public static double YAW_RESTORE_SPEED = 20.0;
    public static double YAW_CHANGE_EPSILON_DEG = 1.0;

    public boolean enable = true;
    public double xPercent = X_PERCENT;
    public double yPercent = Y_PERCENT;
    public int dx = D_X;
    public int dy = D_Y;
    public double offsetY = OFFSET_Y;
    public double scale = SCALE;
    public double cameraRotationHDeg = CAMERA_ROTATION_H_DEG;
    public double cameraRotationVDeg = CAMERA_ROTATION_V_DEG;
    public double rotateLimitHDeg = ROTATE_LIMIT_H_DEG;
    public double rotateLimitVDeg = ROTATE_LIMIT_V_DEG;
    public double yawChangeSpeed = YAW_CHANGE_SPEED;
    public double yawRestoreSpeed = YAW_RESTORE_SPEED;
    public double yawChangeEpsilonDeg = YAW_CHANGE_EPSILON_DEG;
    public boolean mapPitchLimit = true;
    public boolean showBox = false;

    public void reset() {
        enable = true;
        xPercent = X_PERCENT;
        yPercent = Y_PERCENT;
        dx = D_X;
        dy = D_Y;
        offsetY = OFFSET_Y;
        scale = SCALE;
        cameraRotationHDeg = CAMERA_ROTATION_H_DEG;
        cameraRotationVDeg = CAMERA_ROTATION_V_DEG;
        rotateLimitHDeg = ROTATE_LIMIT_H_DEG;
        rotateLimitVDeg = ROTATE_LIMIT_V_DEG;
        yawChangeSpeed = YAW_CHANGE_SPEED;
        yawRestoreSpeed = YAW_RESTORE_SPEED;
        yawChangeEpsilonDeg = YAW_CHANGE_EPSILON_DEG;
        mapPitchLimit = true;
        showBox = false;
    }

}
