package com.gl.util;

import org.come.bean.ConfigureBean;
import java.util.HashMap;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import java.io.File;
import org.dom4j.io.SAXReader;
import org.come.entity.Goodstable;
import java.util.Iterator;
import java.util.List;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Good;
import org.come.until.DDGoodUntil;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.util.Map;

public class Xy2Util
{
    public static Map<String, String> MODEL;
    public static String roleneworold;
    public static String zzs;
    static final String[] ONLY;
    private static Map<String, Configrue> CONFIGURE;
    public static final String CLIENT = "netty";
    public static final String DOWNLOAD = "download";
    static BASE64Encoder base64Encodr;
    static BASE64Decoder base64Decoder;
    private static final String AES = "AES";
    private static final String CRYPT_KEY = "BIGGREEN45977309";
    
    public static boolean isOnly(String goodsName) {
        for (int i = 0; i < Xy2Util.ONLY.length; ++i) {
            if (Xy2Util.ONLY[i].equals(goodsName)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isExist(String goodsName) {
        if (goodsName == null || goodsName.length() == 0) {
            return false;
        }
        List<Good> list2 = DDGoodUntil.ddgood;
        for (Good goods : list2) {
            if (goods == null) {
                continue;
            }
            else if (goods.getGoodstable().getGoodsname().equals(goodsName)) {
                return true;
            }
            else {
                continue;
            }
        }
        Goodstable[] list3;
        for (Goodstable goods2 : list3 = GoodsListFromServerUntil.getGoodslist()) {
            if (goods2 != null && goods2.getGoodsname().equals(goodsName)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isFull() {
        Goodstable[] goods = GoodsListFromServerUntil.getGoodslist();
        for (int j = 0; j < goods.length; ++j) {
            if (goods[j] == null) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); ++i) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static long getProficiency(int lvltrue) {
        switch (lvltrue) {
            case 0: {
                return 10000L;
            }
            case 1: {
                return 15000L;
            }
            case 2: {
                return 20000L;
            }
            case 3: {
                return 25000L;
            }
            case 4: {
                return 25000L;
            }
            default: {
                return 0L;
            }
        }
    }
    
    public static Map<String, Configrue> getConfigure() {
        if (Xy2Util.CONFIGURE.size() != 0) {
            return Xy2Util.CONFIGURE;
        }
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File("resource/other/configure.xml"));
            Element root = document.getRootElement();
            Iterator iterator = root.elementIterator();
            while (iterator.hasNext()) {
                Configrue config = new Configrue();
                Element peopleElement = (Element)iterator.next();
                Iterator childIterator = peopleElement.elementIterator();
                while (childIterator.hasNext()) {
                    Element childPeopleElement = (Element)childIterator.next();
                    if (childPeopleElement.getName().equals("address")) {
                        config.setAddress(decrypt(childPeopleElement.getText()));
                    }
                    else if (childPeopleElement.getName().equals("port")) {
                        config.setPort((int)Integer.valueOf(childPeopleElement.getText()));
                    }
                    else {
                        continue;
                    }
                }
                Xy2Util.CONFIGURE.put(peopleElement.attribute("type").getValue(), config);
            }
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return Xy2Util.CONFIGURE;
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(encrypt("81.70.45.106".getBytes()));
    }
    
    public static String encrypt(byte[] src) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec securekey = new SecretKeySpec("BIGGREEN45977309".getBytes(), "AES");
        cipher.init(1, securekey);
        return Xy2Util.base64Encodr.encode(cipher.doFinal(src));
    }
    
    public static String decrypt(String mm) {
        try {
            byte[] src = Xy2Util.base64Decoder.decodeBuffer(mm);
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec securekey = new SecretKeySpec("BIGGREEN45977309".getBytes(), "AES");
            cipher.init(2, securekey);
            return new String(cipher.doFinal(src));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    static {
        Xy2Util.roleneworold = "新";
        Xy2Util.zzs = "5";
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (configure.getLzjskg() != null) {
            Xy2Util.zzs = configure.getLzjskg();
        }
        if (configure.getNeworold() != null) {
            Xy2Util.roleneworold = configure.getNeworold();
        }
        //角色皮肤
        (Xy2Util.MODEL = new HashMap<>()).put("20002", "500095");
        Xy2Util.MODEL.put("20003", "40003");
        Xy2Util.MODEL.put("20007", "20007");
        Xy2Util.MODEL.put("20009", "40002");
        Xy2Util.MODEL.put("21007", "500093");
        Xy2Util.MODEL.put("21008", "500098");
        Xy2Util.MODEL.put("21009", "43002");
        Xy2Util.MODEL.put("21010", "43001");
        Xy2Util.MODEL.put("22003", "44002");
        Xy2Util.MODEL.put("22005", "500097");
        Xy2Util.MODEL.put("22007", "500094");
        Xy2Util.MODEL.put("22008", "500100");
        Xy2Util.MODEL.put("22009", "44001");
        Xy2Util.MODEL.put("22010", "11021");
        Xy2Util.MODEL.put("23001", "500096");
        Xy2Util.MODEL.put("23003", "500099");
        Xy2Util.MODEL.put("23004", "500102");
        Xy2Util.MODEL.put("23006", "42006");
        Xy2Util.MODEL.put("20003", "11103");
        Xy2Util.MODEL.put("21002", "21103");
        Xy2Util.MODEL.put("21003", "21102");
        Xy2Util.MODEL.put("20006", "12101");
        Xy2Util.MODEL.put("22006", "32102");
        Xy2Util.MODEL.put("20001", "11101");
        Xy2Util.MODEL.put("4294987297", "200010001");
        Xy2Util.MODEL.put("81604398625", "200010001");
        Xy2Util.MODEL.put("26199300525601", "200010001");
        Xy2Util.MODEL.put("8589954593", "11111");
        Xy2Util.MODEL.put("85899365921", "11111");
        Xy2Util.MODEL.put("2000102", "11111");
        Xy2Util.MODEL.put("26225070329377", "11111");
        Xy2Util.MODEL.put("1099511647777", "1110101");
        Xy2Util.MODEL.put("2199023275553", "1110102");
        Xy2Util.MODEL.put("3298534903329", "1110103");
        Xy2Util.MODEL.put("4398046531105", "1110104");
        Xy2Util.MODEL.put("5497558158881", "1110105");
        Xy2Util.MODEL.put("6597069786657", "1110106");
        Xy2Util.MODEL.put("2000201", "11102");
        Xy2Util.MODEL.put("81604398626", "11102");
        Xy2Util.MODEL.put("4294987298", "11102");
        Xy2Util.MODEL.put("26199300525602", "11102");
        Xy2Util.MODEL.put("12884921890", "200020003");
        Xy2Util.MODEL.put("90194333218", "200020003");
        Xy2Util.MODEL.put("26302379740706", "200020003");
        Xy2Util.MODEL.put("1099511647778", "1110201");
        Xy2Util.MODEL.put("2199023275554", "1110202");
        Xy2Util.MODEL.put("3298534903330", "1110203");
        Xy2Util.MODEL.put("4398046531106", "1110204");
        Xy2Util.MODEL.put("5497558158882", "1110205");
        Xy2Util.MODEL.put("6597069786658", "1110206");
        Xy2Util.MODEL.put("17179889187", "11103");
        Xy2Util.MODEL.put("94489300515", "11103");
        Xy2Util.MODEL.put("26293789806115", "11103");
        Xy2Util.MODEL.put("21474856483", "11113");
        Xy2Util.MODEL.put("98784267811", "11113");
        Xy2Util.MODEL.put("26237955231267", "11113");
        Xy2Util.MODEL.put("2000305", "11113");
        Xy2Util.MODEL.put("1099511647779", "1110301");
        Xy2Util.MODEL.put("2199023275555", "1110302");
        Xy2Util.MODEL.put("3298534903331", "1110303");
        Xy2Util.MODEL.put("4398046531107", "1110304");
        Xy2Util.MODEL.put("5497558158883", "1110305");
        Xy2Util.MODEL.put("6597069786659", "1110306");
        Xy2Util.MODEL.put("20004", "12102");
        Xy2Util.MODEL.put("34359758372", "12102");
        Xy2Util.MODEL.put("111669169700", "12102");
        Xy2Util.MODEL.put("26207890460196", "12102");
        Xy2Util.MODEL.put("115964136996", "12112");
        Xy2Util.MODEL.put("38654725668", "12112");
        Xy2Util.MODEL.put("2000409", "12112");
        Xy2Util.MODEL.put("30120605666852", "12112");
        Xy2Util.MODEL.put("1099511647780", "1210201");
        Xy2Util.MODEL.put("2199023275556", "1210202");
        Xy2Util.MODEL.put("3298534903332", "1210203");
        Xy2Util.MODEL.put("4398046531108", "1210204");
        Xy2Util.MODEL.put("5497558158884", "1210205");
        Xy2Util.MODEL.put("6597069786660", "1210206");
        Xy2Util.MODEL.put("20005", "12113");
        Xy2Util.MODEL.put("42949692965", "12113");
        Xy2Util.MODEL.put("120259104293", "12113");
        Xy2Util.MODEL.put("26276609936933", "12113");
        Xy2Util.MODEL.put("107374202405", "12103");
        Xy2Util.MODEL.put("2000507", "12103");
        Xy2Util.MODEL.put("107374202405", "12103");
        Xy2Util.MODEL.put("30064791077", "12103");
        Xy2Util.MODEL.put("26212185427493", "12103");
        Xy2Util.MODEL.put("1099511647781", "1210301");
        Xy2Util.MODEL.put("2199023275557", "1210302");
        Xy2Util.MODEL.put("3298534903333", "1210303");
        Xy2Util.MODEL.put("4398046531109", "1210304");
        Xy2Util.MODEL.put("5497558158885", "1210305");
        Xy2Util.MODEL.put("6597069786661", "1210306");
        Xy2Util.MODEL.put("120259104294", "12101");
        Xy2Util.MODEL.put("42949692966", "12101");
        Xy2Util.MODEL.put("26276609936934", "12101");
        Xy2Util.MODEL.put("47244660262", "12101");
        Xy2Util.MODEL.put("2000612", "12111");
        Xy2Util.MODEL.put("51539627558", "12111");
        Xy2Util.MODEL.put("128849038886", "12111");
        Xy2Util.MODEL.put("26216480394790", "12111");
        Xy2Util.MODEL.put("1099511647782", "1210101");
        Xy2Util.MODEL.put("2199023275558", "1210102");
        Xy2Util.MODEL.put("3298534903334", "1210103");
        Xy2Util.MODEL.put("4398046531110", "1210104");
        Xy2Util.MODEL.put("5497558158886", "1210105");
        Xy2Util.MODEL.put("6597069786662", "1210106");
        Xy2Util.MODEL.put("81604398631", "11104");
        Xy2Util.MODEL.put("4294987303", "11104");
        Xy2Util.MODEL.put("26199300525607", "11104");
        Xy2Util.MODEL.put("98784267815", "11104");
        Xy2Util.MODEL.put("21474856487", "11104");
        Xy2Util.MODEL.put("1099511647783", "1110401");
        Xy2Util.MODEL.put("2199023275559", "1110402");
        Xy2Util.MODEL.put("3298534903335", "1110403");
        Xy2Util.MODEL.put("4398046531111", "1110404");
        Xy2Util.MODEL.put("5497558158887", "1110405");
        Xy2Util.MODEL.put("6597069786663", "1110406");
        Xy2Util.MODEL.put("20008", "12104");
        Xy2Util.MODEL.put("4294987304", "12104");
        Xy2Util.MODEL.put("81604398632", "12104");
        Xy2Util.MODEL.put("26199300525608", "12104");
        Xy2Util.MODEL.put("120259104296", "12104");
        Xy2Util.MODEL.put("42949692968", "12104");
        Xy2Util.MODEL.put("26276609936936", "12104");
        Xy2Util.MODEL.put("1099511647784", "1210401");
        Xy2Util.MODEL.put("2199023275560", "1210402");
        Xy2Util.MODEL.put("3298534903336", "1210403");
        Xy2Util.MODEL.put("4398046531112", "1210404");
        Xy2Util.MODEL.put("5497558158888", "1210405");
        Xy2Util.MODEL.put("6597069786664", "1210406");
        Xy2Util.MODEL.put("85899365929", "11105");
        Xy2Util.MODEL.put("8589954601", "11105");
        Xy2Util.MODEL.put("26225070329385", "11105");
        Xy2Util.MODEL.put("103079235113", "11105");
        Xy2Util.MODEL.put("25769823785", "11105");
        Xy2Util.MODEL.put("1099511647785", "1110501");
        Xy2Util.MODEL.put("2199023275561", "1110502");
        Xy2Util.MODEL.put("3298534903337", "1110503");
        Xy2Util.MODEL.put("4398046531113", "1110504");
        Xy2Util.MODEL.put("5497558158889", "1110505");
        Xy2Util.MODEL.put("6597069786665", "1110506");
        Xy2Util.MODEL.put("20010", "12105");
        Xy2Util.MODEL.put("4294987306", "200100001");
        Xy2Util.MODEL.put("6936372203050", "200100001");
        Xy2Util.MODEL.put("26199300525610", "200100001");
        Xy2Util.MODEL.put("111669169706", "12105");
        Xy2Util.MODEL.put("34359758378", "12105");
        Xy2Util.MODEL.put("26207890460202", "12105");
        Xy2Util.MODEL.put("1099511647786", "1210501");
        Xy2Util.MODEL.put("2199023275562", "1210502");
        Xy2Util.MODEL.put("3298534903338", "1210503");
        Xy2Util.MODEL.put("4398046531114", "1210504");
        Xy2Util.MODEL.put("5497558158890", "1210505");
        Xy2Util.MODEL.put("6597069786666", "1210506");
        Xy2Util.MODEL.put("21001", "21101");
        Xy2Util.MODEL.put("30064792073", "21101");
        Xy2Util.MODEL.put("107374203401", "21101");
        Xy2Util.MODEL.put("26212185428489", "21101");
        Xy2Util.MODEL.put("128849039881", "21111");
        Xy2Util.MODEL.put("2100112", "21111");
        Xy2Util.MODEL.put("51539628553", "21111");
        Xy2Util.MODEL.put("26216480395785", "21111");
        Xy2Util.MODEL.put("1099511648777", "2110101");
        Xy2Util.MODEL.put("2199023276553", "2110102");
        Xy2Util.MODEL.put("3298534904329", "2110103");
        Xy2Util.MODEL.put("4398046532105", "2110104");
        Xy2Util.MODEL.put("5497558159881", "2110105");
        Xy2Util.MODEL.put("6597069787657", "2110106");
        Xy2Util.MODEL.put("42949693962", "21103");
        Xy2Util.MODEL.put("120259105290", "21103");
        Xy2Util.MODEL.put("26276609937930", "21103");
        Xy2Util.MODEL.put("2100213", "21113");
        Xy2Util.MODEL.put("55834595850", "21113");
        Xy2Util.MODEL.put("133144007178", "21113");
        Xy2Util.MODEL.put("26233660264970", "21113");
        Xy2Util.MODEL.put("1099511648778", "2110301");
        Xy2Util.MODEL.put("2199023276554", "2110302");
        Xy2Util.MODEL.put("3298534904330", "2110303");
        Xy2Util.MODEL.put("4398046532106", "2110304");
        Xy2Util.MODEL.put("5497558159882", "2110305");
        Xy2Util.MODEL.put("6597069787658", "2110306");
        Xy2Util.MODEL.put("120259105291", "21112");
        Xy2Util.MODEL.put("42949693963", "21112");
        Xy2Util.MODEL.put("26276609937931", "21112");
        Xy2Util.MODEL.put("2100312", "21102");
        Xy2Util.MODEL.put("51539628555", "21102");
        Xy2Util.MODEL.put("128849039883", "21102");
        Xy2Util.MODEL.put("26216480395787", "21102");
        Xy2Util.MODEL.put("1099511648779", "2110201");
        Xy2Util.MODEL.put("2199023276555", "2110202");
        Xy2Util.MODEL.put("3298534904331", "2110203");
        Xy2Util.MODEL.put("4398046532107", "2110204");
        Xy2Util.MODEL.put("5497558159883", "2110205");
        Xy2Util.MODEL.put("6597069787659", "2110206");
        Xy2Util.MODEL.put("21004", "22103");
        Xy2Util.MODEL.put("120259105292", "22103");
        Xy2Util.MODEL.put("42949693964", "22103");
        Xy2Util.MODEL.put("26276609937932", "22103");
        Xy2Util.MODEL.put("38654726668", "22113");
        Xy2Util.MODEL.put("115964137996", "22113");
        Xy2Util.MODEL.put("26220775363084", "22113");
        Xy2Util.MODEL.put("2100409", "22113");
        Xy2Util.MODEL.put("1099511648780", "2210301");
        Xy2Util.MODEL.put("2199023276556", "2210302");
        Xy2Util.MODEL.put("3298534904332", "2210303");
        Xy2Util.MODEL.put("4398046532108", "2210304");
        Xy2Util.MODEL.put("5497558159884", "2210305");
        Xy2Util.MODEL.put("6597069787660", "2210306");
        Xy2Util.MODEL.put("21005", "22102");
        Xy2Util.MODEL.put("4294988301", "22102");
        Xy2Util.MODEL.put("81604399629", "22102");
        Xy2Util.MODEL.put("26199300526605", "22102");
        Xy2Util.MODEL.put("2100507", "22112");
        Xy2Util.MODEL.put("30064792077", "22112");
        Xy2Util.MODEL.put("107374203405", "22112");
        Xy2Util.MODEL.put("26212185428493", "22112");
        Xy2Util.MODEL.put("1099511648781", "2210201");
        Xy2Util.MODEL.put("2199023276557", "2210202");
        Xy2Util.MODEL.put("3298534904333", "2210203");
        Xy2Util.MODEL.put("4398046532109", "2210204");
        Xy2Util.MODEL.put("5497558159885", "2210205");
        Xy2Util.MODEL.put("6597069787661", "2210206");
        Xy2Util.MODEL.put("111669170702", "22101");
        Xy2Util.MODEL.put("2100608", "22101");
        Xy2Util.MODEL.put("34359759374", "22101");
        Xy2Util.MODEL.put("26207890461198", "22101");
        Xy2Util.MODEL.put("60129563150", "22111");
        Xy2Util.MODEL.put("9878424801806", "22111");
        Xy2Util.MODEL.put("21006", "22111");
        Xy2Util.MODEL.put("1099511648782", "2210101");
        Xy2Util.MODEL.put("2199023276558", "2210102");
        Xy2Util.MODEL.put("3298534904334", "2210103");
        Xy2Util.MODEL.put("4398046532110", "2210104");
        Xy2Util.MODEL.put("5497558159886", "2210105");
        Xy2Util.MODEL.put("6597069787662", "2210106");
        Xy2Util.MODEL.put("128849039887", "210070012");
        Xy2Util.MODEL.put("51539628559", "210070012");
        Xy2Util.MODEL.put("26216480395791", "210070012");
        Xy2Util.MODEL.put("17179890191", "21104");
        Xy2Util.MODEL.put("94489301519", "21104");
        Xy2Util.MODEL.put("5166845678095", "21104");
        Xy2Util.MODEL.put("1099511648783", "2110401");
        Xy2Util.MODEL.put("2199023276559", "2110402");
        Xy2Util.MODEL.put("3298534904335", "2110403");
        Xy2Util.MODEL.put("4398046532111", "2110404");
        Xy2Util.MODEL.put("5497558159887", "2110405");
        Xy2Util.MODEL.put("6597069787663", "2110406");
        Xy2Util.MODEL.put("120259105296", "22104");
        Xy2Util.MODEL.put("42949693968", "22104");
        Xy2Util.MODEL.put("26276609937936", "22104");
        Xy2Util.MODEL.put("124554072592", "22104");
        Xy2Util.MODEL.put("47244661264", "22104");
        Xy2Util.MODEL.put("30146375471632", "22104");
        Xy2Util.MODEL.put("1099511648784", "2210401");
        Xy2Util.MODEL.put("2199023276560", "2210402");
        Xy2Util.MODEL.put("3298534904336", "2210403");
        Xy2Util.MODEL.put("4398046532112", "2210404");
        Xy2Util.MODEL.put("5497558159888", "2210405");
        Xy2Util.MODEL.put("6597069787664", "2210406");
        Xy2Util.MODEL.put("120259105297", "21105");
        Xy2Util.MODEL.put("42949693969", "21105");
        Xy2Util.MODEL.put("26276609937937", "21105");
        Xy2Util.MODEL.put("17179890193", "21105");
        Xy2Util.MODEL.put("94489301521", "21105");
        Xy2Util.MODEL.put("30159260373521", "21105");
        Xy2Util.MODEL.put("1099511648785", "2110501");
        Xy2Util.MODEL.put("2199023276561", "2110502");
        Xy2Util.MODEL.put("3298534904337", "2110503");
        Xy2Util.MODEL.put("4398046532113", "2110504");
        Xy2Util.MODEL.put("5497558159889", "2110505");
        Xy2Util.MODEL.put("6597069787665", "2110506");
        Xy2Util.MODEL.put("21010", "22105");
        Xy2Util.MODEL.put("43001", "22105");
        Xy2Util.MODEL.put("60129563154", "22105");
        Xy2Util.MODEL.put("9878424801810", "22105");
        Xy2Util.MODEL.put("115964138002", "22105");
        Xy2Util.MODEL.put("30120605667858", "22105");
        Xy2Util.MODEL.put("1099511648786", "2210501");
        Xy2Util.MODEL.put("2199023276562", "2210502");
        Xy2Util.MODEL.put("3298534904338", "2210503");
        Xy2Util.MODEL.put("4398046532114", "2210504");
        Xy2Util.MODEL.put("5497558159890", "2210505");
        Xy2Util.MODEL.put("6597069787666", "2210506");
        Xy2Util.MODEL.put("22001", "31101");
        Xy2Util.MODEL.put("2200112", "31101");
        Xy2Util.MODEL.put("128849040881", "31101");
        Xy2Util.MODEL.put("51539629553", "31101");
        Xy2Util.MODEL.put("26216480396785", "31101");
        Xy2Util.MODEL.put("90194335217", "31111");
        Xy2Util.MODEL.put("12884923889", "31111");
        Xy2Util.MODEL.put("26302379742705", "31111");
        Xy2Util.MODEL.put("1099511649777", "3110101");
        Xy2Util.MODEL.put("2199023277553", "3110102");
        Xy2Util.MODEL.put("3298534905329", "3110103");
        Xy2Util.MODEL.put("4398046533105", "3110104");
        Xy2Util.MODEL.put("5497558160881", "3110105");
        Xy2Util.MODEL.put("6597069788657", "3110106");
        Xy2Util.MODEL.put("22002", "31112");
        Xy2Util.MODEL.put("81604400626", "31112");
        Xy2Util.MODEL.put("4294989298", "31112");
        Xy2Util.MODEL.put("2200201", "31112");
        Xy2Util.MODEL.put("26199300527602", "31112");
        Xy2Util.MODEL.put("64424531442", "31102");
        Xy2Util.MODEL.put("26272314971634", "31102");
        Xy2Util.MODEL.put("1099511649778", "3110201");
        Xy2Util.MODEL.put("2199023277554", "3110202");
        Xy2Util.MODEL.put("3298534905330", "3110203");
        Xy2Util.MODEL.put("4398046533106", "3110204");
        Xy2Util.MODEL.put("5497558160882", "3110205");
        Xy2Util.MODEL.put("6597069788658", "3110206");
        Xy2Util.MODEL.put("22003", "400515");
        Xy2Util.MODEL.put("30103425799667", "44002");
        Xy2Util.MODEL.put("26212185429491", "44002");
        Xy2Util.MODEL.put("107374204403", "44002");
        Xy2Util.MODEL.put("30064793075", "44002");
        Xy2Util.MODEL.put("2200314", "400515");
        Xy2Util.MODEL.put("137438975475", "400515");
        Xy2Util.MODEL.put("60129564147", "400515");
        Xy2Util.MODEL.put("9878424802803", "400515");
        Xy2Util.MODEL.put("30107720766963", "400515");
        Xy2Util.MODEL.put("1099511649779", "3110301");
        Xy2Util.MODEL.put("2199023277555", "3110302");
        Xy2Util.MODEL.put("3298534905331", "3110303");
        Xy2Util.MODEL.put("4398046533107", "3110304");
        Xy2Util.MODEL.put("5497558160883", "3110305");
        Xy2Util.MODEL.put("6597069788659", "3110306");
        Xy2Util.MODEL.put("7696581416435", "旧皮肤");
        Xy2Util.MODEL.put("22004", "32103");
        Xy2Util.MODEL.put("120259106292", "32103");
        Xy2Util.MODEL.put("42949694964", "32103");
        Xy2Util.MODEL.put("21474858484", "32113");
        Xy2Util.MODEL.put("2200405", "32113");
        Xy2Util.MODEL.put("98784269812", "32113");
        Xy2Util.MODEL.put("1099511649780", "3210301");
        Xy2Util.MODEL.put("2199023277556", "3210302");
        Xy2Util.MODEL.put("3298534905332", "3210303");
        Xy2Util.MODEL.put("4398046533108", "3210304");
        Xy2Util.MODEL.put("5497558160884", "3210305");
        Xy2Util.MODEL.put("6597069788660", "3210306");
        Xy2Util.MODEL.put("30064793077", "220050007");
        Xy2Util.MODEL.put("107374204405", "220050007");
        Xy2Util.MODEL.put("68719498741", "32111");
        Xy2Util.MODEL.put("146028910069", "32111");
        Xy2Util.MODEL.put("2200516", "32111");
        Xy2Util.MODEL.put("1099511649781", "3210101");
        Xy2Util.MODEL.put("2199023277557", "3210102");
        Xy2Util.MODEL.put("3298534905333", "3210103");
        Xy2Util.MODEL.put("4398046533109", "3210104");
        Xy2Util.MODEL.put("5497558160885", "3210105");
        Xy2Util.MODEL.put("6597069788661", "3210106");
        Xy2Util.MODEL.put("22006", "32112");
        Xy2Util.MODEL.put("81604400630", "32112");
        Xy2Util.MODEL.put("4294989302", "32112");
        Xy2Util.MODEL.put("51539629558", "32102");
        Xy2Util.MODEL.put("2200612", "32102");
        Xy2Util.MODEL.put("128849040886", "32102");
        Xy2Util.MODEL.put("1099511649782", "3210201");
        Xy2Util.MODEL.put("2199023277558", "3210202");
        Xy2Util.MODEL.put("3298534905334", "3210203");
        Xy2Util.MODEL.put("4398046533110", "3210204");
        Xy2Util.MODEL.put("5497558160886", "3210205");
        Xy2Util.MODEL.put("6597069788662", "3210206");
        Xy2Util.MODEL.put("128849040887", "220070112");
        Xy2Util.MODEL.put("51539629559", "220070112");
        Xy2Util.MODEL.put("60129564151", "31104");
        Xy2Util.MODEL.put("137438975479", "31104");
        Xy2Util.MODEL.put("1099511649783", "3110401");
        Xy2Util.MODEL.put("2199023277559", "3110402");
        Xy2Util.MODEL.put("3298534905335", "3110403");
        Xy2Util.MODEL.put("4398046533111", "3110404");
        Xy2Util.MODEL.put("5497558160887", "3110405");
        Xy2Util.MODEL.put("6597069788663", "3110406");
        Xy2Util.MODEL.put("146028910072", "32104");
        Xy2Util.MODEL.put("68719498744", "32104");
        Xy2Util.MODEL.put("47244662264", "32104");
        Xy2Util.MODEL.put("124554073592", "32104");
        Xy2Util.MODEL.put("1099511649784", "3210401");
        Xy2Util.MODEL.put("2199023277560", "3210402");
        Xy2Util.MODEL.put("3298534905336", "3210403");
        Xy2Util.MODEL.put("4398046533112", "3210404");
        Xy2Util.MODEL.put("5497558160888", "3210405");
        Xy2Util.MODEL.put("6597069788664", "3210406");
        Xy2Util.MODEL.put("81604400633", "31105");
        Xy2Util.MODEL.put("4294989305", "31105");
        Xy2Util.MODEL.put("55834596857", "31105");
        Xy2Util.MODEL.put("1099511649785", "3110501");
        Xy2Util.MODEL.put("2199023277561", "3110502");
        Xy2Util.MODEL.put("3298534905337", "3110503");
        Xy2Util.MODEL.put("4398046533113", "3110504");
        Xy2Util.MODEL.put("5497558160889", "3110505");
        Xy2Util.MODEL.put("6597069788665", "3110506");
        Xy2Util.MODEL.put("146028910074", "32105");
        Xy2Util.MODEL.put("68719498746", "32105");
        Xy2Util.MODEL.put("73014466042", "32105");
        Xy2Util.MODEL.put("150323877370", "32105");
        Xy2Util.MODEL.put("1099511649786", "3210501");
        Xy2Util.MODEL.put("2199023277562", "3210502");
        Xy2Util.MODEL.put("3298534905338", "3210503");
        Xy2Util.MODEL.put("4398046533114", "3210504");
        Xy2Util.MODEL.put("5497558160890", "3210505");
        Xy2Util.MODEL.put("6597069788666", "3210506");
        Xy2Util.MODEL.put("4294990297", "41101");//祭剑魂剑皮肤
        Xy2Util.MODEL.put("81604401625", "41101");
        Xy2Util.MODEL.put("2300101", "41111");
        Xy2Util.MODEL.put("42949695961", "41111");
        Xy2Util.MODEL.put("120259107289", "41111");
        Xy2Util.MODEL.put("1099511650777", "4110101");
        Xy2Util.MODEL.put("2199023278553", "4110102");
        Xy2Util.MODEL.put("3298534906329", "4110103");
        Xy2Util.MODEL.put("4398046534105", "4110104");
        Xy2Util.MODEL.put("5497558161881", "4110105");
        Xy2Util.MODEL.put("6597069789657", "4110106");
        Xy2Util.MODEL.put("23002", "41103");
        Xy2Util.MODEL.put("128849041882", "230020112");
        Xy2Util.MODEL.put("51539630554", "230020112");
        Xy2Util.MODEL.put("2300205", "41113");
        Xy2Util.MODEL.put("21474859482", "41113");
        Xy2Util.MODEL.put("98784270810", "41113");
        Xy2Util.MODEL.put("1099511650778", "4110301");
        Xy2Util.MODEL.put("2199023278554", "4110302");
        Xy2Util.MODEL.put("3298534906330", "4110303");
        Xy2Util.MODEL.put("4398046534106", "4110304");
        Xy2Util.MODEL.put("5497558161882", "4110305");
        Xy2Util.MODEL.put("6597069789658", "4110306");
        Xy2Util.MODEL.put("25769826779", "41102");
        Xy2Util.MODEL.put("103079238107", "41102");
        Xy2Util.MODEL.put("2300306", "41102");
        Xy2Util.MODEL.put("55834597851", "41112");
        Xy2Util.MODEL.put("133144009179", "41112");
        Xy2Util.MODEL.put("1099511650779", "4110201");
        Xy2Util.MODEL.put("2199023278555", "4110202");
        Xy2Util.MODEL.put("3298534906331", "4110203");
        Xy2Util.MODEL.put("4398046534107", "4110204");
        Xy2Util.MODEL.put("5497558161883", "4110205");
        Xy2Util.MODEL.put("6597069789659", "4110206");
        Xy2Util.MODEL.put("34359761372", "42101");
        Xy2Util.MODEL.put("111669172700", "42101");
        Xy2Util.MODEL.put("2300409", "42111");
        Xy2Util.MODEL.put("38654728668", "42111");
        Xy2Util.MODEL.put("115964139996", "42111");
        Xy2Util.MODEL.put("1099511650780", "4210101");
        Xy2Util.MODEL.put("2199023278556", "4210102");
        Xy2Util.MODEL.put("3298534906332", "4210103");
        Xy2Util.MODEL.put("4398046534108", "4210104");
        Xy2Util.MODEL.put("5497558161884", "4210105");
        Xy2Util.MODEL.put("6597069789660", "4210106");
        Xy2Util.MODEL.put("23005", "42102");
        Xy2Util.MODEL.put("73014467037", "42102");
        Xy2Util.MODEL.put("150323878365", "42102");
        Xy2Util.MODEL.put("47244663261", "42112");
        Xy2Util.MODEL.put("2300511", "42112");
        Xy2Util.MODEL.put("124554074589", "42112");
        Xy2Util.MODEL.put("1099511650781", "4210201");
        Xy2Util.MODEL.put("2199023278557", "4210202");
        Xy2Util.MODEL.put("3298534906333", "4210203");
        Xy2Util.MODEL.put("4398046534109", "4210204");
        Xy2Util.MODEL.put("5497558161885", "4210205");
        Xy2Util.MODEL.put("6597069789661", "4210206");
        Xy2Util.MODEL.put("47244663262", "42113");
        Xy2Util.MODEL.put("124554074590", "42113");
        Xy2Util.MODEL.put("68719499742", "42103");
        Xy2Util.MODEL.put("2300616", "42103");
        Xy2Util.MODEL.put("146028911070", "42103");
        Xy2Util.MODEL.put("1099511650782", "4210301");
        Xy2Util.MODEL.put("2199023278558", "4210302");
        Xy2Util.MODEL.put("3298534906334", "4210303");
        Xy2Util.MODEL.put("4398046534110", "4210304");
        Xy2Util.MODEL.put("5497558161886", "4210305");
        Xy2Util.MODEL.put("6597069789662", "4210306");
        Xy2Util.MODEL.put("24001", "2400101");
        Xy2Util.MODEL.put("24002", "2400210");
        Xy2Util.MODEL.put("24003", "2400318");
        Xy2Util.MODEL.put("24004", "2400403");
        Xy2Util.MODEL.put("24005", "2400518");
        Xy2Util.MODEL.put("24006", "2400601");
        Xy2Util.MODEL.put("4294991297", "2400101");
        Xy2Util.MODEL.put("25769827777", "2400106");
        Xy2Util.MODEL.put("1099511651777", "24001001");
        Xy2Util.MODEL.put("2199023279553", "24001002");
        Xy2Util.MODEL.put("3298534907329", "24001003");
        Xy2Util.MODEL.put("4398046535105", "24001004");
        Xy2Util.MODEL.put("5497558162881", "24001005");
        Xy2Util.MODEL.put("6597069790657", "24001006");
        Xy2Util.MODEL.put("7696581418433", "24001007");
        Xy2Util.MODEL.put("42949696962", "2400210");
        Xy2Util.MODEL.put("51539631554", "2400210");
        Xy2Util.MODEL.put("1099511651778", "24002001");
        Xy2Util.MODEL.put("2199023279554", "24002002");
        Xy2Util.MODEL.put("3298534907330", "24002003");
        Xy2Util.MODEL.put("4398046535106", "24002004");
        Xy2Util.MODEL.put("5497558162882", "24002005");
        Xy2Util.MODEL.put("6597069790658", "24002006");
        Xy2Util.MODEL.put("7696581418434", "24002007");
        Xy2Util.MODEL.put("77309435331", "2400318");
        Xy2Util.MODEL.put("47244664259", "2400311");
        Xy2Util.MODEL.put("1099511651779", "24003001");
        Xy2Util.MODEL.put("2199023279555", "24003002");
        Xy2Util.MODEL.put("3298534907331", "24003003");
        Xy2Util.MODEL.put("4398046535107", "24003004");
        Xy2Util.MODEL.put("5497558162883", "24003005");
        Xy2Util.MODEL.put("6597069790659", "24003006");
        Xy2Util.MODEL.put("7696581418435", "24003007");
        Xy2Util.MODEL.put("90194337220", "240040301");
        Xy2Util.MODEL.put("38654729668", "2400409");
        Xy2Util.MODEL.put("1099511651780", "24004001");
        Xy2Util.MODEL.put("2199023279556", "24004002");
        Xy2Util.MODEL.put("3298534907332", "24004003");
        Xy2Util.MODEL.put("4398046535108", "24004004");
        Xy2Util.MODEL.put("5497558162884", "24004005");
        Xy2Util.MODEL.put("6597069790660", "24004006");
        Xy2Util.MODEL.put("7696581418436", "24004007");
        Xy2Util.MODEL.put("77309435333", "2400518");
        Xy2Util.MODEL.put("51539631557", "2400512");
        Xy2Util.MODEL.put("1099511651781", "24005001");
        Xy2Util.MODEL.put("2199023279557", "24005002");
        Xy2Util.MODEL.put("3298534907333", "24005003");
        Xy2Util.MODEL.put("4398046535109", "24005004");
        Xy2Util.MODEL.put("5497558162885", "24005005");
        Xy2Util.MODEL.put("6597069790661", "24005006");
        Xy2Util.MODEL.put("7696581418437", "24005007");
        Xy2Util.MODEL.put("4294991302", "2400601");
        Xy2Util.MODEL.put("73014468038", "2400617");
        Xy2Util.MODEL.put("1099511651782", "24006001");
        Xy2Util.MODEL.put("2199023279558", "24006002");
        Xy2Util.MODEL.put("3298534907334", "24006003");
        Xy2Util.MODEL.put("4398046535110", "24006004");
        Xy2Util.MODEL.put("5497558162886", "24006005");
        Xy2Util.MODEL.put("6597069790662", "24006006");
        Xy2Util.MODEL.put("7696581418438", "24006007");
        Xy2Util.MODEL.put("30064791083", "2001107");
        Xy2Util.MODEL.put("4294987307", "20011");
        Xy2Util.MODEL.put("1099511647787", "20011001");
        Xy2Util.MODEL.put("2199023275563", "20011002");
        Xy2Util.MODEL.put("3298534903339", "20011003");
        Xy2Util.MODEL.put("4398046531115", "20011004");
        Xy2Util.MODEL.put("5497558158891", "20011005");
        Xy2Util.MODEL.put("6597069786667", "20011006");
        Xy2Util.MODEL.put("7696581414443", "20011007");
        Xy2Util.MODEL.put("34359758380", "12106");
        Xy2Util.MODEL.put("42949692972", "20012");
        Xy2Util.MODEL.put("1099511647788", "20012001");
        Xy2Util.MODEL.put("2199023275564", "20012002");
        Xy2Util.MODEL.put("3298534903340", "20012003");
        Xy2Util.MODEL.put("4398046531116", "20012004");
        Xy2Util.MODEL.put("5497558158892", "20012005");
        Xy2Util.MODEL.put("6597069786668", "20012006");
        Xy2Util.MODEL.put("7696581414444", "20012007");
        Xy2Util.MODEL.put("42949693971", "2101110");
        Xy2Util.MODEL.put("3015926037352", "21011");
        Xy2Util.MODEL.put("1099511648787", "21011001");
        Xy2Util.MODEL.put("2199023276563", "21011002");
        Xy2Util.MODEL.put("3298534904339", "21011003");
        Xy2Util.MODEL.put("4398046532115", "21011004");
        Xy2Util.MODEL.put("5497558159891", "21011005");
        Xy2Util.MODEL.put("6597069787667", "21011006");
        Xy2Util.MODEL.put("7696581415443", "21011007");
        Xy2Util.MODEL.put("42949693972", "2101210");
        Xy2Util.MODEL.put("1159641325552", "21012");
        Xy2Util.MODEL.put("1099511648788", "21012001");
        Xy2Util.MODEL.put("2199023276564", "21012002");
        Xy2Util.MODEL.put("3298534904340", "21012003");
        Xy2Util.MODEL.put("4398046532116", "21012004");
        Xy2Util.MODEL.put("5497558159892", "21012005");
        Xy2Util.MODEL.put("6597069787668", "21012006");
        Xy2Util.MODEL.put("7696581415444", "21012007");
        Xy2Util.MODEL.put("51539629563", "2201112");
        Xy2Util.MODEL.put("5583455585755", "22011");
        Xy2Util.MODEL.put("1099511649787", "22011001");
        Xy2Util.MODEL.put("2199023277563", "22011002");
        Xy2Util.MODEL.put("3298534905339", "22011003");
        Xy2Util.MODEL.put("4398046533115", "22011004");
        Xy2Util.MODEL.put("5497558160891", "22011005");
        Xy2Util.MODEL.put("6597069788667", "22011006");
        Xy2Util.MODEL.put("7696581416443", "22011007");
        Xy2Util.MODEL.put("47244662268", "2201211");
        Xy2Util.MODEL.put("150323855370", "22012");
        Xy2Util.MODEL.put("1099511649788", "22012001");
        Xy2Util.MODEL.put("2199023277564", "22012002");
        Xy2Util.MODEL.put("3298534905340", "22012003");
        Xy2Util.MODEL.put("4398046533116", "22012004");
        Xy2Util.MODEL.put("5497558160892", "22012005");
        Xy2Util.MODEL.put("6597069788668", "22012006");
        Xy2Util.MODEL.put("7696581416444", "22012007");
        Xy2Util.MODEL.put("42949695967", "2300710");
        Xy2Util.MODEL.put("124554075589", "23007");
        Xy2Util.MODEL.put("1099511650783", "23007001");
        Xy2Util.MODEL.put("2199023278559", "23007002");
        Xy2Util.MODEL.put("3298534906335", "23007003");
        Xy2Util.MODEL.put("4398046534111", "23007004");
        Xy2Util.MODEL.put("5497558161887", "23007005");
        Xy2Util.MODEL.put("6597069789663", "23007006");
        Xy2Util.MODEL.put("7696581417439", "23007007");
        Xy2Util.MODEL.put("4294990304", "2300816");
        Xy2Util.MODEL.put("146028955070", "23008");
        Xy2Util.MODEL.put("1099511650784", "23008001");
        Xy2Util.MODEL.put("2199023278560", "23008002");
        Xy2Util.MODEL.put("3298534906336", "23008003");
        Xy2Util.MODEL.put("4398046534112", "23008004");
        Xy2Util.MODEL.put("5497558161888", "23008005");
        Xy2Util.MODEL.put("6597069789664", "23008006");
        Xy2Util.MODEL.put("7696581417440", "23008007");
        Xy2Util.MODEL.put("4294991303", "2400701");
        Xy2Util.MODEL.put("51539635557", "24007");
        Xy2Util.MODEL.put("1099511651783", "24007001");
        Xy2Util.MODEL.put("2199023279559", "24007002");
        Xy2Util.MODEL.put("3298534907335", "24007003");
        Xy2Util.MODEL.put("4398046535111", "24007004");
        Xy2Util.MODEL.put("5497558162887", "24007005");
        Xy2Util.MODEL.put("6597069790663", "24007006");
        Xy2Util.MODEL.put("7696581418439", "24007007");
        Xy2Util.MODEL.put("73014468040", "2400817");
        Xy2Util.MODEL.put("73014465538", "24008");
        Xy2Util.MODEL.put("1099511651784", "24008001");
        Xy2Util.MODEL.put("2199023279560", "24008002");
        Xy2Util.MODEL.put("3298534907336", "24008003");
        Xy2Util.MODEL.put("4398046535112", "24008004");
        Xy2Util.MODEL.put("5497558162888", "24008005");
        Xy2Util.MODEL.put("6597069790664", "24008006");
        Xy2Util.MODEL.put("7696581418440", "24008007");
        Xy2Util.MODEL.put("20011", "20011");
        Xy2Util.MODEL.put("20012", "20012");
        Xy2Util.MODEL.put("21011", "21011");
        Xy2Util.MODEL.put("21012", "21012");
        Xy2Util.MODEL.put("22011", "22011");
        Xy2Util.MODEL.put("22012", "22012");
        Xy2Util.MODEL.put("23007", "23007");
        Xy2Util.MODEL.put("23008", "23008");
        Xy2Util.MODEL.put("24007", "24007");
        Xy2Util.MODEL.put("24008", "24008");
        ONLY = new String[] { "元气蛋" };
        Xy2Util.CONFIGURE = new HashMap<>();
        Xy2Util.base64Encodr = new BASE64Encoder();
        Xy2Util.base64Decoder = new BASE64Decoder();

    }
    public static Map<String, String> NEWMODEL;

    static {

        NEWMODEL = new HashMap<String, String>();
        // 武器末尾编号
        // 剑:1 扇:2 锤:3 斧头:4 拳套:5 书:6 棍:7 鞭:8	钩:9 刀:10 双环:11 枪:12 幡:13 爪:14 浮尘:15 飘带:16 灯笼:17 弓:18
        //逍遥生
     //   NEWMODEL.put("20001", "5000101");
        //NEWMODEL.put("81604398625", "5000101");//剑
   //     NEWMODEL.put("85899365921", "5000102");//扇子

     //   NEWMODEL.put("4294987297", "5000101");//替换逍遥生剑武器皮肤
     //   NEWMODEL.put("26199300525601", "5000101");//替换逍遥生剑武器皮肤
    //    NEWMODEL.put("8589954593", "5000102");//逍遥生扇子武器皮肤
    //    NEWMODEL.put("2000102", "5000102");//替换逍遥生剑武器皮肤
    //    NEWMODEL.put("26225070329377", "5000102");//逍遥生扇子武器皮肤
        //剑侠客
       // NEWMODEL.put("20002", "5000201");//剑
      //  NEWMODEL.put("81604398626", "5000201");//剑
      //  NEWMODEL.put("90194333218", "5000203");//锤子

     //   NEWMODEL.put("4294987298", "5000201");//剑侠客的剑皮肤
     //   NEWMODEL.put("26199300525602", "5000201");//剑侠客的剑皮肤
   //     NEWMODEL.put("12884921890", "5000203");//剑侠客的锤子皮肤
     //   NEWMODEL.put("26302379740706", "5000203");//剑侠客的锤子皮肤
        //猛壮士
        NEWMODEL.put("20003", "5000304");
        NEWMODEL.put("94489300515", "5000303");//斧子
        NEWMODEL.put("98784267811", "5000304");//拳套

        NEWMODEL.put("17179889187", "5000303");//猛壮士的斧头皮肤
        NEWMODEL.put("26293789806115", "5000303");//猛壮士的斧头皮肤
        NEWMODEL.put("21474856483", "5000304");//猛壮士的拳套皮肤
        NEWMODEL.put("26237955231267", "5000304");//猛壮士的拳套皮肤
        NEWMODEL.put("2000305", "5000304");//猛壮士的拳套皮肤
        //飞燕女
        NEWMODEL.put("20004", "5000408");
        NEWMODEL.put("115964136996", "5000409");//钩
        NEWMODEL.put("111669169700", "5000408");//鞭

        NEWMODEL.put("34359758372", "5000408");//飞燕女的鞭子皮肤
        NEWMODEL.put("26207890460196", "5000408");//飞燕女的鞭子皮肤
        NEWMODEL.put("38654725668", "5000409");//飞燕女的钩子皮肤
        NEWMODEL.put("2000409", "5000409");//飞燕女的钩子皮肤
        NEWMODEL.put("30120605666852", "5000409");//飞燕女的钩子皮肤
        //英女侠
        NEWMODEL.put("20005", "5000507");
        NEWMODEL.put("107374202405", "5000507");//棍
        NEWMODEL.put("120259104293", "5000510");//刀

        NEWMODEL.put("42949692965", "5000510");//英女侠的刀皮肤
        NEWMODEL.put("26276609936933", "5000510");//英女侠的刀皮肤
        NEWMODEL.put("2000507", "5000507");//英女侠的棍皮肤
        NEWMODEL.put("30064791077", "5000507");//英女侠的棍皮肤
        NEWMODEL.put("26212185427493", "5000507");//英女侠的棍皮肤
        //俏千金
//        NEWMODEL.put("20006", "5000612");
//        NEWMODEL.put("120259104294", "5000610");//刀
//        NEWMODEL.put("128849038886", "5000612");//枪
//
//        NEWMODEL.put("42949692966", "5000610");//俏千金的刀皮肤
//        NEWMODEL.put("26276609936934", "5000610");//俏千金的刀皮肤
//        NEWMODEL.put("47244660262", "5000610");//俏千金的刀皮肤
//        NEWMODEL.put("2000612", "5000612");//俏千金的枪皮肤
//        NEWMODEL.put("51539627558", "5000612");//俏千金的枪皮肤
//        NEWMODEL.put("26216480394790", "5000612");//俏千金的枪皮肤
        //飞剑侠
        NEWMODEL.put("20007", "5000701");
       // NEWMODEL.put("81604398631", "5000701");//剑
        NEWMODEL.put("98784267815", "5000705");//拳套

      //  NEWMODEL.put("4294987303", "5000701");//飞剑侠的剑皮肤
     //   NEWMODEL.put("26199300525607", "5000701");//飞剑侠的剑皮肤
        NEWMODEL.put("21474856487", "5000705");//飞剑侠的拳套皮肤
        //燕山雪
        NEWMODEL.put("20008", "5000801");
        NEWMODEL.put("81604398632", "5000801");//剑
        NEWMODEL.put("120259104296", "5000810");//刀

        NEWMODEL.put("4294987304", "5000801");//燕山雪的剑皮肤
        NEWMODEL.put("26199300525608", "5000801");//燕山雪的剑皮肤
        NEWMODEL.put("42949692968", "5000810");//燕山雪的刀皮肤
        NEWMODEL.put("26276609936936", "5000810");//燕山雪的刀皮肤
        //纯阳子
        NEWMODEL.put("20009", "5000902");
//        NEWMODEL.put("85899365929", "5000902");//扇
        NEWMODEL.put("103079235113", "5000906");//书
//
//        NEWMODEL.put("8589954601", "5000902");//纯阳子的扇子皮肤
//        NEWMODEL.put("26225070329385", "5000902");//纯阳子的扇子皮肤
        NEWMODEL.put("25769823785", "5000906");//纯阳子的书皮肤
        //红拂女
        NEWMODEL.put("20010", "5001001");
   //     NEWMODEL.put("81604398634", "5001001");//剑
        NEWMODEL.put("111669169706", "5001007");//鞭

      //  NEWMODEL.put("4294987306", "5001001");//红拂女的剑皮肤
      // NEWMODEL.put("26199300525610", "5001001");//红拂女的剑皮肤
        NEWMODEL.put("34359758378", "5001007");//红拂女的鞭子皮肤
        NEWMODEL.put("26207890460202", "5001007");//红拂女的鞭子皮肤
        //虎头怪
        NEWMODEL.put("21001", "5100107");
//        NEWMODEL.put("107374203401", "5100107");//棍
//        NEWMODEL.put("128849039881", "5100112");//枪
//
//        NEWMODEL.put("30064792073", "5100107");//虎头怪的棍皮肤
//        NEWMODEL.put("26212185428489", "5100107");//虎头怪的棍皮肤
//        NEWMODEL.put("2100112", "5100112");//虎头怪的枪皮肤
//        NEWMODEL.put("51539628553", "5100112");//虎头怪的枪皮肤
//        NEWMODEL.put("26216480395785", "5100112");//虎头怪的枪皮肤
        //夺命妖
        NEWMODEL.put("21002", "5100210");
        NEWMODEL.put("120259105290", "5100210");//刀
        NEWMODEL.put("133144007178", "5100213");//幡

        NEWMODEL.put("42949693962", "5100210");//夺命妖的刀皮肤
        NEWMODEL.put("26276609937930", "5100210");//夺命妖的刀皮肤
        NEWMODEL.put("2100213", "5100213");//夺命妖的幡皮肤
        NEWMODEL.put("55834595850", "5100213");//夺命妖的幡皮肤
        NEWMODEL.put("26233660264970", "5100213");//夺命妖的幡皮肤
        //巨魔王
        NEWMODEL.put("21003", "5100310");
        NEWMODEL.put("120259105291", "5100310");
        NEWMODEL.put("128849039883", "5100312");

        NEWMODEL.put("42949693963", "5100310");//巨魔王的刀皮肤
        NEWMODEL.put("26276609937931", "5100310");//巨魔王的刀皮肤
        NEWMODEL.put("2100312", "5100312");//巨魔王的枪皮肤
        NEWMODEL.put("51539628555", "5100312");//巨魔王的枪皮肤
        NEWMODEL.put("26216480395787", "5100312");//巨魔王的枪皮肤
        //小蛮妖
        NEWMODEL.put("21004", "5100409");
        NEWMODEL.put("115964137996", "5100409");
        NEWMODEL.put("120259105292", "5100410");

        NEWMODEL.put("42949693964", "5100410");//小蛮妖的刀皮肤
        NEWMODEL.put("26276609937932", "5100409");//小蛮妖的钩子皮肤
        NEWMODEL.put("38654726668", "5100409");//小蛮妖的钩子皮肤
        NEWMODEL.put("26220775363084", "5100409");//小蛮妖的钩子皮肤
        NEWMODEL.put("2100409", "5100409");//小蛮妖的钩子皮肤
        //骨精灵
//        NEWMODEL.put("21005", "5100501");
//        NEWMODEL.put("81604399629", "5100501");
//        NEWMODEL.put("107374203405", "5100507");
//
//        NEWMODEL.put("4294988301", "5100501");//骨精灵的剑皮肤
//        NEWMODEL.put("26199300526605", "5100501");//骨精灵的剑皮肤
//        NEWMODEL.put("2100507", "5100507");//骨精灵的棍皮肤
//        NEWMODEL.put("30064792077", "5100507");//骨精灵的棍皮肤
//        NEWMODEL.put("26212185428493", "5100507");//骨精灵的棍皮肤
        //狐美人
//        NEWMODEL.put("21006", "5100614");//这个用老的hh的狐狸模型
        NEWMODEL.put("111669170702", "5100608");
//        NEWMODEL.put("137438974478", "5100614");

//        NEWMODEL.put("2100608", "22101");//狐美人的鞭子皮肤
//        NEWMODEL.put("34359759374", "22101");//狐美人的鞭子皮肤
//        NEWMODEL.put("26207890461198", "22101");//狐美人的鞭子皮肤
        NEWMODEL.put("60129563150", "5100614");//狐美人的爪子皮肤
        NEWMODEL.put("9878424801806", "5100614");//狐美人的爪子皮肤
        NEWMODEL.put("21006", "5100614");//狐美人的爪子皮肤
        //逆天魔
        NEWMODEL.put("21007", "5100712");
        NEWMODEL.put("128849039887", "5100712");
        NEWMODEL.put("94489301519", "5100704");

        NEWMODEL.put("51539628559", "5100712");//逆天魔的枪皮肤
        NEWMODEL.put("26216480395791", "5100712");//逆天魔的枪皮肤
        NEWMODEL.put("17179890191", "5100704");//逆天魔的斧头皮肤
        NEWMODEL.put("5166845678095", "5100704");//逆天魔的斧头皮肤
        //媚灵狐
        NEWMODEL.put("21008", "5100810");
//        NEWMODEL.put("120259105296", "5100810");
//        NEWMODEL.put("124554072592", "5100811");
//
//        NEWMODEL.put("42949693968", "5100810");//媚灵狐的刀皮肤
 //       NEWMODEL.put("26276609937936", "5100810");//媚灵狐的刀皮肤
        NEWMODEL.put("47244661264", "5100811");//媚灵狐的双环皮肤
        NEWMODEL.put("30146375471632", "5100811");//媚灵狐的双环皮肤
        //混天魔
        NEWMODEL.put("21009", "5100904");
//        NEWMODEL.put("120259105297", "5100910");
        NEWMODEL.put("94489301521", "5100904");
////
//        NEWMODEL.put("42949693969", "5100910");//混天魔的刀皮肤
//        NEWMODEL.put("26276609937937", "5100910");//混天魔的刀皮肤
        NEWMODEL.put("17179890193", "5100904");//混天魔的斧头皮肤
        //九尾狐
        NEWMODEL.put("21010", "5101014");
        NEWMODEL.put("137438974482", "5101014");
        NEWMODEL.put("115964138002", "5101009");

        NEWMODEL.put("43001", "5101014");//九尾狐的爪子皮肤
        NEWMODEL.put("60129563154", "5101014");//九尾狐的爪子皮肤
        NEWMODEL.put("9878424801810", "5101014");//九尾狐的爪子皮肤
        NEWMODEL.put("30120605667858", "5101009");//九尾狐的钩子皮肤
        //神天兵
//        NEWMODEL.put("22001", "31111");
//        NEWMODEL.put("90194335217", "31111");
  //      NEWMODEL.put("128849040881", "5200112");

 //       NEWMODEL.put("2200112", "5200112");//神天兵的枪皮肤
 //       NEWMODEL.put("51539629553", "5200112");//神天兵的枪皮肤
  //      NEWMODEL.put("26216480396785", "5200112");//神天兵的枪皮肤
//        NEWMODEL.put("12884923889", "31111");//神天兵的锤子皮肤
//        NEWMODEL.put("26302379742705", "31111");//神天兵的锤子皮肤
 //       NEWMODEL.put("26302379742705", "5200103");//神天兵的锤子皮肤
        //智圣仙
//        NEWMODEL.put("22002", "5200201");
//        NEWMODEL.put("81604400626", "5200201");
//        NEWMODEL.put("64424531442", "5200215");
//
//        NEWMODEL.put("4294989298", "5200201");//智圣仙的剑皮肤
//        NEWMODEL.put("2200201", "5200201");//智圣仙的剑皮肤
//        NEWMODEL.put("26199300527602", "5200201");//智圣仙的剑皮肤
//        NEWMODEL.put("26272314971634", "5200215");//智圣仙的拂尘皮肤
        //龙战将
//        NEWMODEL.put("22003", "5200307");
//        NEWMODEL.put("107374204403", "5200307");
//        NEWMODEL.put("137438975475", "5200314");
//
//        NEWMODEL.put("30103425799667", "5200307");//龙战将的棍皮肤
//        NEWMODEL.put("26212185429491", "5200307");//龙战将的棍皮肤
//        NEWMODEL.put("30064793075", "5200307");//龙战将的棍皮肤
//        NEWMODEL.put("2200314", "5200314");//龙战将的爪子皮肤
//        NEWMODEL.put("60129564147", "5200314");//龙战将的爪子皮肤
//        NEWMODEL.put("9878424802803", "5200314");//龙战将的爪子皮肤
//        NEWMODEL.put("30107720766963", "5200314");//龙战将的爪子皮肤
        //精灵仙
//        NEWMODEL.put("22004", "5200410");
//        NEWMODEL.put("120259106292", "5200410");//光武
        NEWMODEL.put("98784269812", "5200405");
//
//        NEWMODEL.put("42949694964", "5200410");//精灵仙的刀皮肤
        NEWMODEL.put("21474858484", "5200405");//精灵仙的拳套皮肤
        NEWMODEL.put("2200405", "5200405");//精灵仙的拳套皮肤
        //舞天姬
  //      NEWMODEL.put("22005", "5200507");
//        NEWMODEL.put("107374204405", "5200507");
//        NEWMODEL.put("146028910069", "5200516");
//
//        NEWMODEL.put("30064793077", "5200507");//舞天姬的棍皮肤
//        NEWMODEL.put("68719498741", "5200516");//舞天姬的丝带皮肤
//        NEWMODEL.put("2200516", "5200516");//舞天姬的丝带皮肤
        //玄剑娥
        NEWMODEL.put("22006", "5200601");
        NEWMODEL.put("81604400630", "5200601");
        NEWMODEL.put("128849040886", "5200612");

        NEWMODEL.put("4294989302", "5200601");//玄剑娥的剑皮肤
        NEWMODEL.put("51539629558", "5200612");//玄剑娥的枪皮肤
        NEWMODEL.put("2200612", "5200612");//玄剑娥的枪皮肤
        //武尊神
     //   NEWMODEL.put("22007", "5200712");
      //  NEWMODEL.put("128849040887", "5200712");
     //   NEWMODEL.put("137438975479", "5200714");

      //  NEWMODEL.put("51539629559", "5200712");//武尊神的枪皮肤
     //   NEWMODEL.put("60129564151", "5200714");//武尊神的爪子皮肤
        //玄天姬
//        NEWMODEL.put("22008", "5200816");
     //   NEWMODEL.put("146028910072", "5200816");
        NEWMODEL.put("124554073592", "5200811");

      //  NEWMODEL.put("68719498744", "5200816");//玄天姬的丝带皮肤
        NEWMODEL.put("47244662264", "5200811");//玄天姬的双环皮肤
        //紫薇神
        NEWMODEL.put("22009", "5200901");
        NEWMODEL.put("81604400633", "5200901");//光武剑
        NEWMODEL.put("133144008185", "5200913");

        NEWMODEL.put("4294989305", "5200901");//紫薇神的剑皮肤
        NEWMODEL.put("55834596857", "5200913");//紫薇神的幡皮肤
        //霓裳仙
        NEWMODEL.put("22010", "5201016");
        NEWMODEL.put("146028910074", "5201016");//光武飘带
        NEWMODEL.put("150323877370", "5201017");

        NEWMODEL.put("68719498746", "5201016");//霓裳仙的丝带皮肤
        NEWMODEL.put("73014466042", "5201017");//霓裳仙的灯笼皮肤
        //祭剑魂
        NEWMODEL.put("23001", "5300101");
//        NEWMODEL.put("81604401625", "5300101");//光武剑
        NEWMODEL.put("120259107289", "5300110");
//
//        NEWMODEL.put("4294990297", "5300101");//祭剑魂的剑皮肤
//        NEWMODEL.put("2300101", "5300101");//祭剑魂的剑皮肤
        NEWMODEL.put("42949695961", "5300110");//祭剑魂的刀皮肤
        //猎魂引
        NEWMODEL.put("23002", "5300205");
        NEWMODEL.put("98784270810", "5300205");
    //    NEWMODEL.put("128849041882", "5300212");

     //   NEWMODEL.put("51539630554", "5300212");//猎魂引的枪皮肤
        NEWMODEL.put("2300205", "5300205");//猎魂引的拳套皮肤
        NEWMODEL.put("21474859482", "5300205");//猎魂引的拳套皮肤
        //无崖子
        NEWMODEL.put("23003", "5300306");
        NEWMODEL.put("103079238107", "5300306");
        NEWMODEL.put("133144009179", "5300313");

        NEWMODEL.put("25769826779", "5300306");//无崖子的书皮肤
        NEWMODEL.put("2300306", "5300306");//无崖子的书皮肤
        NEWMODEL.put("55834597851", "5300313");//无崖子的幡皮肤
        //墨衣行
        NEWMODEL.put("23004", "5300408");
        NEWMODEL.put("111669172700", "5300408");
        NEWMODEL.put("115964139996", "5300409");

        NEWMODEL.put("34359761372", "5300408");//墨衣行的鞭子皮肤
        NEWMODEL.put("2300409", "5300409");//墨衣行的钩子皮肤
        NEWMODEL.put("38654728668", "5300409");//墨衣行的钩子皮肤
        //夜溪灵
//        NEWMODEL.put("23005", "5300511");
        NEWMODEL.put("124554074589", "5300511");
//        NEWMODEL.put("150323878365", "5300517");//光武灯笼
//
//        NEWMODEL.put("73014467037", "5300517");//夜溪灵的灯笼皮肤
        NEWMODEL.put("47244663261", "5300511");//夜溪灵的双环皮肤
        NEWMODEL.put("2300511", "5300511");//夜溪灵的双环皮肤
        //幽梦影
        NEWMODEL.put("23006", "5300611");
        NEWMODEL.put("124554074590", "5300611");
        NEWMODEL.put("146028911070", "5300616");

        NEWMODEL.put("47244663262", "5300611");//幽梦影的双环皮肤
        NEWMODEL.put("68719499742", "5300616");//幽梦影的丝带皮肤
        NEWMODEL.put("2300616", "5300616");//幽梦影的丝带皮肤
        //沧浪君
//        NEWMODEL.put("24001", "5400101");
//        NEWMODEL.put("81604402625", "5400101");//光武剑
//        NEWMODEL.put("103079239105", "5400106");
//
//        NEWMODEL.put("4294991297", "5400101");//沧浪君的剑皮肤
//        NEWMODEL.put("25769827777", "5400106");//沧浪君的书皮肤
        //龙渊客
        NEWMODEL.put("24002", "5400212");
        NEWMODEL.put("128849042882", "5400212");
        NEWMODEL.put("120259108290", "5400210");

        NEWMODEL.put("42949696962", "5400210");//龙渊客的刀皮肤
        NEWMODEL.put("51539631554", "5400212");//龙渊客的枪皮肤
        //忘忧子
        NEWMODEL.put("24003", "5400318");
        NEWMODEL.put("154618846659", "5400318");
        NEWMODEL.put("124554075587", "5400311");

        NEWMODEL.put("77309435331", "5400318");//忘忧子的弓皮肤
        NEWMODEL.put("47244664259", "5400311");//忘忧子的双环皮肤
        //骊珠儿
        NEWMODEL.put("24004", "5400403");
        NEWMODEL.put("90194337220", "5400403");
        NEWMODEL.put("115964140996", "5400409");

        NEWMODEL.put("12884925892", "5400403");//骊珠儿的锤子皮肤
        NEWMODEL.put("38654729668", "5400409");//骊珠儿的钩子皮肤
        //木兰行
        NEWMODEL.put("24005", "5400512");
//        NEWMODEL.put("128849042885", "5400512");//光武枪
        NEWMODEL.put("154618846661", "5400518");

        NEWMODEL.put("77309435333", "5400518");//木兰行的弓皮肤
//        NEWMODEL.put("51539631557", "5400512");//木兰行的枪皮肤
        //莫解语
        NEWMODEL.put("24006", "5400601");
        NEWMODEL.put("81604402630", "5400601");
        NEWMODEL.put("150323879366", "5400617");

        NEWMODEL.put("4294991302", "5400601");//莫解语的剑皮肤
        NEWMODEL.put("73014468038", "5400617");//莫解语的灯笼皮肤
        // 武器末尾编号
        // 剑:1 扇:2 锤:3 斧头:4 拳套:5 书:6 棍:7 鞭:8	钩:9 刀:10 双环:11 枪:12 幡:13 爪:14 浮尘:15 飘带:16 灯笼:17 弓:18


//        //神秀生
//        NEWMODEL.put("4294987307", "20011");
//        NEWMODEL.put("30064791083", "20011");
//
//        //玲珑女
//        NEWMODEL.put("42949692972", "20012");
//        NEWMODEL.put("34359758380", "20012");
//        //绝影魔
//        NEWMODEL.put("42949693971", "21011");//刀
//        NEWMODEL.put("51539628563", "21011");//枪
//        //霜月灵
//        NEWMODEL.put("38654726676", "21012");//钩
//        NEWMODEL.put("42949693972", "21012");//刀
//
//        //青阳使
//        NEWMODEL.put("51539629563", "22011");//枪
//        NEWMODEL.put("30064793083", "22011");//棍
//        //云中君
//        NEWMODEL.put("68719498748", "22012");//飘带
//        NEWMODEL.put("47244662268", "22012");//环
//        //南冠客
//        NEWMODEL.put("4294990303", "23007");//剑
//        NEWMODEL.put("42949695967", "23007");//刀
//        //镜花影
//        NEWMODEL.put("4294990304", "23008");//剑
//        NEWMODEL.put("68719499744", "23008");//飘带
//        //游无极
//        NEWMODEL.put("4294991303", "24007");//剑
//        NEWMODEL.put("42949696967", "24007");//刀
//        //临九渊
//        NEWMODEL.put("68719500744", "24008");//飘带
//        NEWMODEL.put("73014468040", "24008");//灯笼

    }
}
