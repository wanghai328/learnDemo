package com.example.wh.mytestjianshu;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Util {
    public static ContentValues videoContentValues = null;

    public static String getRecordingTimeFromMillis(long millis) {
        String strRecordingTime = null;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;

        if (hours >= 0 && hours < 10)
            strRecordingTime = "0" + hours + ":";
        else
            strRecordingTime = hours + ":";

        if (hours > 0)
            minutes = minutes % 60;

        if (minutes >= 0 && minutes < 10)
            strRecordingTime += "0" + minutes + ":";
        else
            strRecordingTime += minutes + ":";

        seconds = seconds % 60;

        if (seconds >= 0 && seconds < 10)
            strRecordingTime += "0" + seconds;
        else
            strRecordingTime += seconds;

        return strRecordingTime;

    }

    public static int determineDisplayOrientation(Activity activity,
                                                  int defaultCameraId) {
        int displayOrientation = 0;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
            CameraInfo cameraInfo = new CameraInfo();
            Camera.getCameraInfo(defaultCameraId, cameraInfo);

            int degrees = getRotationAngle(activity);

            if (cameraInfo.facing == CameraInfo.CAMERA_FACING_FRONT) {
                displayOrientation = (cameraInfo.orientation + degrees) % 360;
                displayOrientation = (360 - displayOrientation) % 360;
            } else {
                displayOrientation = (cameraInfo.orientation - degrees + 360) % 360;
            }
        }
        return displayOrientation;
    }

    public static int getRotationAngle(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;

        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;

            case Surface.ROTATION_90:
                degrees = 90;
                break;

            case Surface.ROTATION_180:
                degrees = 180;
                break;

            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        return degrees;
    }

    public static int getRotationAngle(int rotation) {
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;

            case Surface.ROTATION_90:
                degrees = 90;
                break;

            case Surface.ROTATION_180:
                degrees = 180;
                break;

            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        return degrees;
    }

    public static void deleteTempVideo(Context context) {
        final String dirPath = Environment.getExternalStorageDirectory()
                + "/Android/data/" + context.getPackageName() + "/video";
        new Thread(new Runnable() {

            @Override
            public void run() {
                File file = new File(dirPath);
                if (file != null && file.isDirectory()) {
                    File files[] = file.listFiles();
                    if (files != null) {
                        for (File file2 : files) {
                            file2.delete();
                        }
                    }
                }
            }
        }).start();
    }


    //海报保存的地址
    public static String getPosterSavePath(Context context) {
        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/posterpage";
        File file = new File(result);
        if (file.exists() && file.isDirectory()) {
        } else {
            file.mkdirs();
        }
        return result;
    }

    //视频裁剪地址
    public static File getAppCropVideoTmpPath(Context context) {

        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/cropvideo";

        File cropvideo = new File(result);

        if (!cropvideo.exists()) {
            if (!cropvideo.mkdirs()) {
                // TODO: 16/6/14  file make failed!
            }
        }

        return cropvideo;
    }

    //视频封面地址
    public static String getAppVideoCoverPath(Context context) {
        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/videocover";
        File file = new File(result);
        if (file.exists() && file.isDirectory()) {

        } else {
            file.mkdirs();
        }
        return result;
    }

    //支付成功图片存储
    public static String getAppOrderDownLoadTmpPath(Context context) {
        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/order";
        File file = new File(result);
        if (file.exists() && file.isDirectory()) {

        } else {
            file.mkdirs();
        }
        return result;
    }

    //视频裁剪VideoJoin 保存合成的视频地址
    public static String getAppJoinVideoTmpPath(Context context) {
        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/joinvideo";
        File file = new File(result);
        if (file.exists() && file.isDirectory()) {
        } else {
            file.mkdirs();
        }
        return result;
    }

    //视频合成Editactivity 保存合成的视频地址
    public static String getAppEditVideoTmpPath(Context context) {
        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/editvideo";
        File file = new File(result);
        if (file.exists() && file.isDirectory()) {
        } else {
            file.mkdirs();
        }
        return result;
    }

    //创建视频 拍摄成功的临时文件夹
    public static String getAppShotVideoTmpPath(Context context) {
        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/videoshot";
        File file = new File(result);
        if (file.exists() && file.isDirectory()) {
        } else {
            file.mkdirs();
        }
        return result;
    }

    //拍摄视频返回的时候 把视频保存到系统可以识别的位置
    public static String getAppSysVideoSavePath(Context context) {
        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/videosys";
        File file = new File(result);
        if (file.exists() && file.isDirectory()) {
        } else {
            file.mkdirs();
        }
        return result;
    }

    //创建录音地址 临时文件夹
    public static String getAppLuMusicTmpPath(Context context) {
        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/peimusic";
        File file = new File(result);
        if (file.exists() && file.isDirectory()) {
        } else {
            file.mkdirs();
        }
        return result;
    }

    //创建录音地址 最终合成的录音路径
    public static String getAppLuMusicFinalPath(Context context) {
        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/lumusic_finish";
        File file = new File(result);
        if (file.exists() && file.isDirectory()) {
        } else {
            file.mkdirs();
        }
        return result;
    }

    //创建拍照文件的 临时文件夹
    public static File getAppShootTmpPath(Context context) {
        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/shoot";
        File file = new File(result);
        if (file.exists() && file.isDirectory()) {
        } else {
            file.mkdirs();
        }
        return file;
    }

    //获取所有草稿箱图片文件的地址
    public static String getAppAllPhotoPath() {
        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/editpic";
        File file = new File(result);
        if (file.exists() && file.isDirectory()) {
        } else {
            file.mkdirs();
        }
        return result;
    }

    public static boolean isSDCardValid(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState()))
            return true;
        return false;
    }


    public static class ResolutionComparator implements Comparator<Camera.Size> {
        @Override
        public int compare(Camera.Size size1, Camera.Size size2) {
            if (size1.height != size2.height)
                return size1.height - size2.height;
            else
                return size1.width - size2.width;
        }
    }

    public static void concatenateMultipleFiles(String inpath, String outpath) {
        File Folder = new File(inpath);
        File files[];
        files = Folder.listFiles();

        if (files.length > 0 && files != null) {
            for (int i = 0; i < files.length; i++) {
                Reader in = null;
                Writer out = null;
                try {
                    in = new FileReader(files[i]);
                    out = new FileWriter(outpath, true);
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static HashMap<String, String> getMetaData() {
        HashMap<String, String> localHashMap = new HashMap<String, String>();
        localHashMap.put("creation_time", new SimpleDateFormat(
                "yyyy_MM_dd_HH_mm_ss_SSSZ").format(new Date()));
        return localHashMap;
    }

    public static int getTimeStampInNsFromSampleCounted(int paramInt) {
        return (int) (paramInt / 0.0441D);
    }

    public static Toast showToast(Context context, String textMessage,
                                  int timeDuration) {
        if (null == context) {
            return null;
        }
        textMessage = (null == textMessage ? "Oops! " : textMessage.trim());
        Toast t = Toast.makeText(context, textMessage, timeDuration);
        t.show();
        return t;
    }

    // public IplImage getFrame(String filePath) {
    // CvCapture capture = cvCreateFileCapture(filePath);
    // IplImage image = cvQueryFrame(capture);
    // return image;
    // }

    public static byte[] rotateYUV420Degree90(byte[] data, int imageWidth,
                                              int imageHeight) {

        byte[] yuv = new byte[imageWidth * imageHeight * 3 / 2];
        // Rotate the Y luma
        int i = 0;
        for (int x = 0; x < imageWidth; x++) {
            for (int y = imageHeight - 1; y >= 0; y--) {
                yuv[i] = data[y * imageWidth + x];
                i++;
            }

        }
        // Rotate the U and V color components
        i = imageWidth * imageHeight * 3 / 2 - 1;
        for (int x = imageWidth - 1; x > 0; x = x - 2) {
            for (int y = 0; y < imageHeight / 2; y++) {
                yuv[i] = data[(imageWidth * imageHeight) + (y * imageWidth) + x];
                i--;
                yuv[i] = data[(imageWidth * imageHeight) + (y * imageWidth)
                        + (x - 1)];
                i--;
            }
        }
        return yuv;
    }

    /**
     * 冰冻特效
     */
    public static Bitmap ice(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.RGB_565);
        int dst[] = new int[width * height];
        bmp.getPixels(dst, 0, width, 0, 0, width, height);
        int R, G, B, pixel;
        int pos, pixColor;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pos = y * width + x;
                pixColor = dst[pos]; // 获取图片当前点的像素值
                R = Color.red(pixColor); // 获取RGB三原色
                G = Color.green(pixColor);
                B = Color.blue(pixColor);
                pixel = R - G - B;
                pixel = pixel * 3 / 2;
                if (pixel < 0)
                    pixel = -pixel;
                if (pixel > 255)
                    pixel = 255;
                R = pixel; // 计算后重置R值，以下类同
                pixel = G - B - R;
                pixel = pixel * 3 / 2;
                if (pixel < 0)
                    pixel = -pixel;
                if (pixel > 255)
                    pixel = 255;
                G = pixel;
                pixel = B - R - G;
                pixel = pixel * 3 / 2;
                if (pixel < 0)
                    pixel = -pixel;
                if (pixel > 255)
                    pixel = 255;
                B = pixel;
                dst[pos] = Color.rgb(R, G, B); // 重置当前点的像素值
            } // x
        } // y
        bitmap.setPixels(dst, 0, width, 0, 0, width, height);
        return bitmap;
    }

    /**
     * 灰度效果（黑白照片）
     */
    public static Bitmap toGrayscale(Bitmap bmp) {
        int height = bmp.getHeight();
        int width = bmp.getWidth();
        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
                Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmp, 0, 0, paint);
        return bmpGrayscale;
    }

    /**
     * 解码
     */
    public static void decodeYUV420SP(int[] rgb, byte[] yuv420sp, int width,
                                      int height) {
        final int frameSize = width * height;
        for (int j = 0, yp = 0; j < height; j++) {
            int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
            for (int i = 0; i < width; i++, yp++) {
                int y = (0xff & ((int) yuv420sp[yp])) - 16;
                if (y < 0)
                    y = 0;
                if ((i & 1) == 0) {
                    v = (0xff & yuv420sp[uvp++]) - 128;
                    u = (0xff & yuv420sp[uvp++]) - 128;
                }
                int y1192 = 1192 * y;
                int r = (y1192 + 1634 * v);
                int g = (y1192 - 833 * v - 400 * u);
                int b = (y1192 + 2066 * u);

                if (r < 0)
                    r = 0;
                else if (r > 262143)
                    r = 262143;
                if (g < 0)
                    g = 0;
                else if (g > 262143)
                    g = 262143;
                if (b < 0)
                    b = 0;
                else if (b > 262143)
                    b = 262143;
                rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000)
                        | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
            }
        }
    }

    public static byte[] rotateYUV420Degree180(byte[] data, int imageWidth,
                                               int imageHeight) {
        byte[] yuv = new byte[imageWidth * imageHeight * 3 / 2];
        int i = 0;
        int count = 0;

        for (i = imageWidth * imageHeight - 1; i >= 0; i--) {
            yuv[count] = data[i];
            count++;
        }

        i = imageWidth * imageHeight * 3 / 2 - 1;
        for (i = imageWidth * imageHeight * 3 / 2 - 1; i >= imageWidth
                * imageHeight; i -= 2) {
            yuv[count++] = data[i - 1];
            yuv[count++] = data[i];
        }
        return yuv;
    }

    public static byte[] rotateYUV420Degree270(byte[] data, int imageWidth,
                                               int imageHeight) {
        byte[] yuv = new byte[imageWidth * imageHeight * 3 / 2];
        int nWidth = 0, nHeight = 0;
        int wh = 0;
        int uvHeight = 0;
        if (imageWidth != nWidth || imageHeight != nHeight) {
            nWidth = imageWidth;
            nHeight = imageHeight;
            wh = imageWidth * imageHeight;
            uvHeight = imageHeight >> 1;// uvHeight = height / 2
        }

        // 旋转Y
        int k = 0;
        for (int i = 0; i < imageWidth; i++) {
            int nPos = 0;
            for (int j = 0; j < imageHeight; j++) {
                yuv[k] = data[nPos + i];
                k++;
                nPos += imageWidth;
            }
        }

        for (int i = 0; i < imageWidth; i += 2) {
            int nPos = wh;
            for (int j = 0; j < uvHeight; j++) {
                yuv[k] = data[nPos + i];
                yuv[k + 1] = data[nPos + i + 1];
                k += 2;
                nPos += imageWidth;
            }
        }
        // 这一部分可以直接旋转270度，但是图像颜色不对
        // // Rotate the Y luma
        // int i = 0;
        // for(int x = imageWidth-1;x >= 0;x--)
        // {
        // for(int y = 0;y < imageHeight;y++)
        // {
        // yuv[i] = data[y*imageWidth+x];
        // i++;
        // }
        //
        // }
        // // Rotate the U and V color components
        // i = imageWidth*imageHeight;
        // for(int x = imageWidth-1;x > 0;x=x-2)
        // {
        // for(int y = 0;y < imageHeight/2;y++)
        // {
        // yuv[i] = data[(imageWidth*imageHeight)+(y*imageWidth)+x];
        // i++;
        // yuv[i] = data[(imageWidth*imageHeight)+(y*imageWidth)+(x-1)];
        // i++;
        // }
        // }
        return rotateYUV420Degree180(yuv, imageWidth, imageHeight);
    }

    public static byte[] cropYUV420(byte[] data, int imageW, int imageH,
                                    int newImageH) {
        int cropH;
        int i, j, count, tmp;
        byte[] yuv = new byte[imageW * newImageH * 3 / 2];

        cropH = (imageH - newImageH) / 2;

        count = 0;
        for (j = cropH; j < cropH + newImageH; j++) {
            for (i = 0; i < imageW; i++) {
                yuv[count++] = data[j * imageW + i];
            }
        }

        // Cr Cb
        tmp = imageH + cropH / 2;
        for (j = tmp; j < tmp + newImageH / 2; j++) {
            for (i = 0; i < imageW; i++) {
                yuv[count++] = data[j * imageW + i];
            }
        }

        return yuv;
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * px = dp * (dpi / 160)
     *
     * @param ctx
     * @param dip
     * @return
     */
    public static int dipToPX(final Context ctx, float dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dip, ctx.getResources().getDisplayMetrics());
    }

    /**
     * sp*ppi/160 =px
     *
     * @param ctx
     * @param sp
     * @return
     */
    public static int spToPX(final Context ctx, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                ctx.getResources().getDisplayMetrics());
    }

    public static String generateUploadKey() {
        String uuid = Util.generateUUID();
        String uploadKey = Util.get32MD5(uuid);
        return uploadKey.substring(8, 24);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * MD5 32位加密方法一 小写
     *
     * @param s
     * @return
     */

    public final static String get32MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            // 使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                // System.out.println((int)b);
                // 将没个数(int)b进行双字节加密
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
    public static int getDensityDpi(Context context){
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.densityDpi;
    }

    /**
     * 获取状态栏的高
     */
    public static int getStatusBarHeight(Activity context) {
        Rect frame = new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        if(0 == statusBarHeight) {
            statusBarHeight = getStatusBarHeightByReflection(context);
        }
        return statusBarHeight;
    }
    public static int getStatusBarHeightByReflection(Context context) {
        Class<?> c;
        Object obj;
        Field field;
        // 默认为38，貌似大部分是这样的
        int x, statusBarHeight = 38;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    public static String getDeviceId(Context context) {
        String id = null;
        try {
            TelephonyManager mTelephonyManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (mTelephonyManager != null) {
                id = mTelephonyManager.getDeviceId();
            }
            if (id == null) {
                id = "unknown";
            }
        } catch (Exception e) {
            id = "unknown";
        }
        return id;
    }

    public static String getAppVersionName(Context context) {
        String versionName = "default";
        synchronized (context) {
            try {
                PackageManager mPackageManager = context.getPackageManager();
                PackageInfo mPackageInfo;
                mPackageInfo = mPackageManager.getPackageInfo(
                        context.getPackageName(), 0);
                versionName = mPackageInfo.versionName;
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
        return versionName;
    }

    public static int getAppVersionCode(Context context) {
        int versionCode = -1;
        synchronized (context) {
            try {
                PackageManager mPackageManager = context.getPackageManager();
                PackageInfo mPackageInfo;
                mPackageInfo = mPackageManager.getPackageInfo(
                        context.getPackageName(), 0);
                versionCode = mPackageInfo.versionCode;
            } catch (Exception e) {

            }
        }
        return versionCode;
    }

    public static String getChannel(Context context) {
        String channel = "default";
        synchronized (context) {
            try {
                ApplicationInfo appInfo = context.getPackageManager()
                        .getApplicationInfo(context.getPackageName(),
                                PackageManager.GET_META_DATA);
                channel = appInfo.metaData.getString("UMENG_CHANNEL");

            } catch (Exception e) {

            }
        }
        return channel;
    }

    // //显示缓存
    public static double getDirSize(File file) {
        // 判断文件是否存在
        if (file != null && file.exists()) {
            // 如果是目录则递归计算其内容的总大小
            File[] children = file.listFiles();
            if (file.isDirectory() && children != null) {
                double size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {// 如果是文件则直接返回其大小,以“兆”为单位
                double size = (double) file.length() / 1024 / 1024;
                return size;
            }
        } else {
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
            return 0.0;
        }
    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */

    public static final int NETTYPE_NONE = 0X00;
    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

    public static int getNetworkType(Context context) {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (extraInfo != null && !extraInfo.isEmpty()) {
                if (extraInfo.equalsIgnoreCase("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }

    }



    public static int getRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }



    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        return perm == 0;
    }


    public static String getAppAudioTmpPath(Context context) {
        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/audio";
        File file = new File(result);
        if (file.exists() && file.isDirectory()) {
        } else {
            file.mkdirs();
        }
        return result;
    }

    /**
     * 微信是否安装
     *
     * @param context
     * @return
     */
    public static boolean isWeiXinInstalled(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * 图片格式
     *
     * @param str
     * @return
     */
    public static List<String> getImageType(String str) {
        List<String> arrayList = new ArrayList();
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                Object obj = null;
                String name = file.getName();
                if (name.indexOf(".") >= 0 || name.indexOf("_") >= 0 || name.length() <= 16) {
                    name = name.substring(name.lastIndexOf(".") + 1, name.length()).toLowerCase();
                    if (name.equals("jpg") || name.equals("gif") || name.equals("png") || name.equals("jpeg") || name.equals("bmp")) {
                        int i = 1;
                    } else {
                        obj = null;
                    }
                } else {
                    obj = 1;
                }
                if (obj != null) {
                    arrayList.add(file.getPath());
                }
            }
        }
        return arrayList;
    }

    //签到打卡相关
    public static String getSignPath(Context context) {
        String result = Environment.getExternalStorageDirectory()
                + "/encodeTmp/sign";
        File file = new File(result);
        if (file.exists() && file.isDirectory()) {

        } else {
            file.mkdirs();
        }
        return result;
    }

    //5.0以上的版本
    public static void translucentStatusBar(Activity activity, boolean hideStatusBarBackground) {
        Window window = activity.getWindow();
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (hideStatusBarBackground) {
            //如果为全透明模式，取消设置Window半透明的Flag
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置状态栏为透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(Color.TRANSPARENT);
            }
            //设置window的状态栏不可见
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            //如果为半透明模式，添加设置Window半透明的Flag
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置系统状态栏处于可见状态
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
        //view不根据系统窗口来调整自己的布局
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
    }
   /* static void translucentStatusBar(Activity activity) {
        Window window = activity.getWindow();
        //设置Window为透明
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View mContentChild = mContentView.getChildAt(0);

        //移除已经存在假状态栏则,并且取消它的Margin间距
        removeFakeStatusBarViewIfExist(activity);
        removeMarginTopOfContentChild(mContentChild, getStatusBarHeight(activity));
        if (mContentChild != null) {
            //fitsSystemWindow 为 false, 不预留系统栏位置.
            ViewCompat.setFitsSystemWindows(mContentChild, false);
        }
    }*/


   //修改状态栏颜色（5.0以上版本）
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarColor(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        //取消状态栏透明
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
            window.setStatusBarColor(statusColor);

        //设置系统状态栏处于可见状态
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        //让view不根据系统窗口来调整自己的布局
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
    }

}
