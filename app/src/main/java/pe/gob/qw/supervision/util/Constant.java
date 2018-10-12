package pe.gob.qw.supervision.util;

public class Constant {
    public static final String IMEI_TEST = "357713087768062";

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    public static final int TOTAL_PREG_SUP = 431;

    public static final int REQUEST_TAKE_PHOTO = 111;
    public static final int REQUEST_PLAY_SERVICES = 222;
    public static final int REQUEST_SETTINGS_ACTIVITY = 555;

    public static final int PERMISSION_C_WES = 100;
    public static final int PERMISSION_RPS_AFL_C_WES = 999;

    public static final int PERMISSION_RPS_INFO = 1;
    public static final int PERMISSION_RPS_VER = 2;
    public static final int PERMISSION_RPS = 3;
    public static final int PERMISSION_AFL = 4;
    public static final int PERMISSION_C = 5;
    public static final int PERMISSION_WES = 6;
    public static final int PERMISSION_WES_BCKP = 7;
    public static final int PERMISSION_WES_RESTORE = 8;

    public static final int REQUEST_STOCK_MONITOREO = 2018;
    public static final int REQUEST_RESUMEN_SUPERVISION = 555;

    public static final int CODIGO_FICHA_MONITOREO_PRODUCTOS = 63;
    public static final int CODIGO_FICHA_MONITOREO_RACIONES = 64;
    public static final int CODIGO_FICHA_CONSUMO_PRODUCTOS = 65;
    public static final int CODIGO_FICHA_CONSUMO_RACIONES = 66;
    public static final int CODIGO_FICHA_SUPERVISION_PRODUCTOS = 67;
    public static final int CODIGO_FICHA_SUPERVISION_RACIONES = 68;
    public static final int CODIGO_PROCESO = 8;

    public static final String CATEGORIA_FICHA_MONITOREO = "X";
    public static final String CATEGORIA_FICHA_CONSUMO = "C";
    public static final String CATEGORIA_FICHA_SUPERVISION = "E";

    public static final int CODIGO_CONTROL_CHECKBOX = 1;
    public static final int CODIGO_CONTROL_EDITTEXT = 2;
    public static final int CODIGO_CONTROL_SPINNER = 8;
    public static final int CODIGO_CONTROL_HORA = 9;
    public static final int CODIGO_CONTROL_SWITCH = 7;
    public static final int CODIGO_CONTROL_RADIOBUTTON = 5;
    public static final int CODIGO_CONTROL_BUTTON = 10;

    public static final String ESTADO_INICIAL = "0";
    public static final String ESTADO_DETALLE = "1";
    public static final String ESTADO_ASISTENCIA = "2";

    public static final String ESTADO_MOTIVO = "3";

    public static final String ESTADO_ALMACEN = "4";

    public static final String ESTADO_ACTA_MAIN = "5";
    public static final String ESTADO_ACTA_DETALLE = "6";

    public static final String ESTADO_CONSUMO_DESAYUNO = "1";

    public static final String ESTADO_CONSUMO_ALMUERZO = "2";


    public static final String ESTADO_FINALIZADO = "7";
    public static final String ESTADO_ENVIADO_INCOMPLETO = "8";
    public static final String ESTADO_ENVIADO_COMPLETO = "9";

    public static final String ESTADO_DESACTIVADO = "10";
    public static final String ESTADO_OCULTO = "11";

    public static final String QW_FOLDER_BACKUP = "QWIIEE/Backup";
    public static final String QW_FOLDER_FOTOS = "QWIIEE/Fotos";
    public static final String QW_FOLDER_UPLOAD = "QWIIEE/Upload";

    public static final String BASE_URL = "http://devmovil.qaliwarma.gob.pe/QWE241/svAndroid.svc/";
    public static final String REST_GRABAR_REGISTROS = "https://app.qaliwarma.gob.pe/QWIIEE029/AndroidService.svc/registro/insert";
    public static final String REST_GRABAR_FOTOS = "https://app.qaliwarma.gob.pe/QWIIEE029/AndroidService.svc/foto/insert";
}
