package pe.gob.qw.supervision.util;

import pe.gob.qw.supervision.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Location;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {

    private static final String TAG = Util.class.getSimpleName();

    public static int sizeImageFile(final String nombre) {
        File file = new File(Environment.getExternalStorageDirectory(), Constant.QW_FOLDER_FOTOS + "/" + nombre);
        return Integer.parseInt(String.valueOf(file.length()/1024));
    }

    public static String ObtenerVersion(Context context){

        String versionAplicacion="";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionAplicacion = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            versionAplicacion="";
            e.printStackTrace();
        }
        return versionAplicacion;

    }

    public static boolean deleteImage(String imagePath) {
        File imageFile = new File(imagePath);
        return imageFile.exists() && imageFile.delete();
    }

    public static String encodeImage(String path)
    {
        File imagefile = new File(path);
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(imagefile);
        }catch(FileNotFoundException e){
            Log.w(TAG, e.getMessage());
        }
        Bitmap bm = BitmapFactory.decodeStream(fis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        //Base64.de
        return encImage;

    }

    public static long dateStringToMillis(String dateTime) {
        long timeInMillis = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date mDate = sdf.parse(dateTime);
            timeInMillis = mDate.getTime();
            System.out.println("DateTime in millis = " + timeInMillis);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timeInMillis;
    }

    //Convert Date to Calendar
    public static Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    //Convert Calendar to Date
    public static Date calendarToDate(Calendar calendar) {
        return calendar.getTime();
    }

    public static long diferenceTime(long inicioDateTime, long finDateTime, String tipo) {
        long result = 0;

        if (inicioDateTime != 0 && finDateTime != 0) {

            result = /*(finDateTime > inicioDateTime) ? (finDateTime - inicioDateTime) : (*/inicioDateTime - finDateTime/*)*/;

            long seconds = result / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;

            switch (tipo) {
                case "s": //segundos
                    result = seconds;
                    break;
                case "m": //minutos
                    result = minutes;
                    break;
                case "h": //horas
                    result = hours;
                    break;
                case "d": //dias
                    result = days;
                    break;
            }
        }

        return result;
    }

    @NonNull
    public static Boolean diferenciaFechas(String fNow, String fSistema)  {

        if (fNow == null || fSistema == null || fNow.equals("") || fSistema.equals("")) {
            return true;
        }

        DateFormat sistemFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DateFormat gpsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date strDateSistema = null;
        Date strDateNow = null;

        try {
            strDateSistema = sistemFormat.parse(fSistema);
        } catch (ParseException e) {
            Log.w(TAG, e.getMessage());
        }

        try {
            strDateNow = gpsFormat.parse(fNow);
        } catch (ParseException e) {
            Log.w(TAG, e.getMessage());
        }

        boolean result = true;

        if (strDateSistema != null && strDateNow != null) {
            if (strDateNow.before(strDateSistema)) {
                result = false;
            }
        }

        return result;
    }

    public static String reemplazarCaracteresEspeciales(String cadena) {
        return (cadena != null ? cadena : "").replaceAll("[á|à|Á|À]", "A").replaceAll("[é|è|É|È]", "E").replaceAll("[í|ì|Í|Ì]", "I").replaceAll("[ó|ò|Ó|Ò]", "O").replaceAll("[ú|ù|Ú|Ù]", "U").replaceAll("[ñ|Ñ]", "N").replaceAll("[^-a-zA-Z0-9 ]+", "");
    }

    public static boolean isOnline(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public static double calcularDistancia(String latitud1, String longitud1, String latitud2, String longitud2) {

        Location locationIE = new Location("ieLocation");
        locationIE.setLatitude(Double.parseDouble(latitud1));
        locationIE.setLongitude(Double.parseDouble(longitud1));

        Location locationME = new Location("meLocation");
        locationME.setLatitude(Double.parseDouble(latitud2));
        locationME.setLongitude(Double.parseDouble(longitud2));

        return locationME.distanceTo(locationIE);
    }

    static public double redondear(double numero, int decimales) {
        return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static String GetIMEI(Activity activity) {
        String imei = "000000000000000";
        TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);

        if (telephonyManager.getDeviceId() != null) imei = telephonyManager.getDeviceId();

        return imei;
    }

    public static boolean checkGooglePlayServices(Activity activity) {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(activity);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(activity, result, Constant.REQUEST_PLAY_SERVICES).show();
            }
            return false;
        }
        return true;
    }

    public static void backupDatabase(final Activity activity){

        MaterialDialog.Builder alertBackup = Util.okCancelAlertDialog(activity,"Copia de Seguridad","¿Desea sacar una copia de seguridad de la base de datos local?", R.drawable.ic_database_black);
        alertBackup.positiveText("SI").negativeText("NO").onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                try {
                    File sd = new File(Environment.getExternalStorageDirectory() + "/" + Constant.QW_FOLDER_BACKUP);
                    if (!sd.exists()) sd.mkdirs();

                    String currentDBPath = Environment.getDataDirectory() + "/data/" + activity.getPackageName() + "/databases/qwiiee-e";
                    String backupDBPath = "qwiiee-e_" + Util.getToday2();
                    File currentDB = new File(currentDBPath);
                    File backupDB = new File(sd, backupDBPath);

                    if (currentDB.exists()) {

                        FileChannel src = null;
                        FileChannel dst = null;

                        try {
                            src = new FileInputStream(currentDB).getChannel();
                            dst = new FileOutputStream(backupDB).getChannel();
                            dst.transferFrom(src, 0, src.size());
                        } catch (Exception ignored){
                            Log.e(TAG, ignored.getMessage());
                        } finally {
                            if (src != null) {
                                src.close();
                            }
                            if (dst != null) {
                                dst.close();
                            }
                        }

                    }
                    dialog.dismiss();

                    Util.simpleAlertDialog(activity,
                            "Respaldo",
                            "Se ha generado la copia de respaldo en la ruta:\n'/QWIIEE/Backup/" + backupDBPath + "'.",
                            R.drawable.ic_assignment_black, false);
                }
                catch (Exception e) {
                    dialog.dismiss();
                    Toast.makeText(activity.getApplicationContext(),
                            "Error generando Copia de Respaldo!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        }).show();
    }

    public static void restoreDatabase(final Activity activity){
        MaterialDialog.Builder alertBackup = Util.okCancelAlertDialog(activity, "Restaurar Backup","¿Desea restaurar la copia de respaldo de la base de datos?", R.drawable.ic_database_black);
        alertBackup.positiveText("SI").negativeText("NO").onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                restoreActionDB(activity);
                dialog.dismiss();
            }
        }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        }).show();
    }

    public static void restoreActionDB(Activity activity){
        try {
            File data = Environment.getDataDirectory();
            File sd = Environment.getExternalStorageDirectory();
            if (sd.canWrite()) {
                String currentDBPath = "//data//" + activity.getPackageName() + "//databases//" + "qwiiee-e";
                String backupDBPath = "/" + Constant.QW_FOLDER_BACKUP + "/" + "qwiiee-e"; // From SD directory.
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (backupDB.canRead()) {
                    FileChannel src = null;
                    FileChannel dst = null;

                    try {
                        src = new FileInputStream(backupDB).getChannel();
                        dst = new FileOutputStream(currentDB).getChannel();
                        dst.transferFrom(src, 0, src.size());
                    } catch (Exception ignored){
                        Log.e(TAG, ignored.getMessage());
                    } finally {
                        if (src != null) {
                            src.close();
                        }
                        if (dst != null) {
                            dst.close();
                        }
                    }

                    Util.simpleAlertDialog(activity, "Restauración", "Copia de respaldo Restaurada con éxito.\n\nLa App se reiniciará.",R.drawable.ic_assignment_black, true);
                } else {
                    Toast.makeText(activity, "No se puede leer la copia de respaldo a restaurar.", Toast.LENGTH_SHORT).show();
                }

            }
        } catch (Exception e) {
            Toast.makeText(activity.getApplicationContext(), "Importación Fallida!", Toast.LENGTH_SHORT).show();
        }
    }

    private static MaterialDialog.Builder okCancelAlertDialog(Context context, String titulo, String mensaje, int restId) {
        return new MaterialDialog.Builder(context)
                .title(titulo)
                .content(mensaje)
                .iconRes(restId)
                .titleColorRes(R.color.colorPrimaryDark)
                .contentColorRes(android.R.color.black)
                .positiveColorRes(R.color.colorPrimaryDark);
        //.negativeColorRes(R.color.colorPrimary);
    }

    private static void simpleAlertDialog(final Activity activity, String titulo, String mensaje, int restId, final boolean isFinish) {
        new MaterialDialog.Builder(activity)
                .title(titulo)
                .titleColorRes(R.color.colorPrimaryDark)
                .content(mensaje)
                .iconRes(restId)
                .contentColorRes(android.R.color.black)
                .positiveColorRes(R.color.colorPrimaryDark)
                .positiveText("ACEPTAR")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();

                        if (isFinish) {
                            int pid = android.os.Process.myPid();
                            android.os.Process.killProcess(pid);
                        }
                    }
                }).show();
    }

    public static String getToday() {
        return android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString();
    }

    public static String getToday2() {
        return android.text.format.DateFormat.format("yyyyMMdd_HHmmss", new Date()).toString();
    }

    public static String getDateToday() {
        return android.text.format.DateFormat.format("yyyy-MM-dd", new Date()).toString();
    }

    public static String getHourToday() {
        return android.text.format.DateFormat.format("HH:mm", new Date()).toString();
    }

    public static String getDateAsDate(Date date) {
        return android.text.format.DateFormat.format("yyyy-MM-dd", date).toString();
    }

    public static String getTimeAsTime(Long time) {
        return android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss", time).toString();
    }

    @Nullable
    public static String createImageFile(Context context) {
        String imageFileName = "IIEE_" + Util.getToday2() + "_";
        try {
            File storageDir = new File(Environment.getExternalStorageDirectory(), Constant.QW_FOLDER_FOTOS);
            storageDir.mkdirs();

            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
            return image.getAbsolutePath();
        } catch (IOException e) {
            Log.w(TAG, e.getMessage());
            return null;
        }
    }

    /** le baja el tamaño a la imagen.
     * */
    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize, boolean filter) {
        float ratio = Math.min(maxImageSize / realImage.getWidth(), maxImageSize / realImage.getHeight());
        int width = Math.round(ratio * realImage.getWidth());
        int height = Math.round(ratio * realImage.getHeight());

        return Bitmap.createScaledBitmap(realImage, width, height, filter);
    }

    /** si la imagen esta rotada, o mal posicionada, este metodo lo rota de forma portrair.
     * */
    public static Bitmap rotateBitmap(@NonNull Bitmap bitmap, @NonNull String mImageFileLocation){
        ExifInterface exifInterface = null;

        try{
            exifInterface = new ExifInterface(mImageFileLocation);
        }catch(IOException e){
            Log.w(TAG, e.getMessage());
        }

        int orientation = 0;

        if (exifInterface != null) {
            orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
        }

        Matrix matrix = new Matrix();
        switch (orientation){
            case ExifInterface.ORIENTATION_ROTATE_90: matrix.setRotate(90);break;
            case ExifInterface.ORIENTATION_ROTATE_180: matrix.setRotate(180);break;
            case ExifInterface.ORIENTATION_ROTATE_270: matrix.setRotate(270);break;
            default:
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /** Primero, disminuye la calidad un 50 % a la imagen y luego lo guarda.
     * */
    public static void storeBitmap(Bitmap bitmap, String filename){
        FileOutputStream out = null;
        try {

            File file = new File(filename);

            if(file.exists()) {
                file.delete();
            }

            out = new FileOutputStream(filename);
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            Log.w(TAG, e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                Log.w(TAG, e.getMessage());
            }
        }
    }

    public static Bitmap decodeSampledBitmapFromResource(String nombre, int reqWidth, int reqHeight) {
        File imageFile = new File(Environment.getExternalStorageDirectory(), Constant.QW_FOLDER_FOTOS + "/" + nombre);
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static String getBase64(Bitmap bm) {
        String dato64="";
        if (bm != null) {
            byte[] Imagen;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 90, stream);
            Imagen= stream.toByteArray();
            dato64= Base64.encodeToString(Imagen, Base64.NO_WRAP);
        }
        return dato64;
    }
}
