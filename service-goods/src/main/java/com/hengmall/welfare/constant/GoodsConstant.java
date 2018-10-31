package com.hengmall.welfare.constant;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 道具物品常量类
 * @author 海乐乐
 * @date 2018年8月10日
 */
public class GoodsConstant {

	/** 金币 **/
	public static final Integer GOODS_MONEY = 1;

	/** 钻石 **/
	public static final Integer GOODS_JEWEL = 2;

	/** 精力**/
	public static final Integer GOODS_VIGOUR=3;

	/** 钓鱼券 **/
	//public static final Integer GOODS_VOUCHER = 3;

	/** 雨露  **/
	public static final Integer GOODS_DEW = 4;

	/**阳光**/
	public static final int GOODS_SUNSHINE=5;

	/** 精华 **/
	//public static final Integer GOODS_ESSENCES = 5;

	/** 海螺 **/
	public static final Integer GOODS_CONCH = 6;

	/** 木头 **/
	public static final Integer GOODS_WOOD = 7;

	/** 双倍金币卡  **/
	public static final Integer GOODS_GOLD_CARD = 8;

	/** 喇叭 **/
	public static final Integer GOODS_HORN = 9;

	/** 小丑鱼 **/
	public static final Integer GOODS_FISH_CLOWN = 10;

	/** 金枪鱼  **/
	public static final Integer GOODS_FISH_TUNAS = 11;

	/** 灯笼鱼 **/
	public static final Integer GOODS_FISH_LANTERN = 12;

	/** 海蝠鱼  **/
	public static final Integer GOODS_FISH_BATS = 13;

	/** 蝴蝶鱼 **/
	public static final Integer GOODS_FISH_BUTTERFLY = 14;

	/** 孔雀鱼 **/
	public static final Integer GOODS_FISH_GUPPY = 15;

	/** 鲨鱼  **/
	public static final Integer GOODS_FISH_SHARK = 16;

	/** 美人鱼 **/
	public static final Integer GOODS_FISH_MERMAID = 17;

	/** 旺财  **/
	public static final Integer GOODS_DOG_MONEY = 18;

	/** 来福 **/
	public static final Integer GOODS_DOG_BLESSINGS = 19;

	/** 金毛  **/
	public static final Integer GOODS_DOG_HAIR = 20;

	/** 藏獒 **/
	public static final Integer GOODS_DOG_MASTIFF = 21;

	/** 带肉牛大骨  **/
	public static final Integer GOODS_MEAT_BONE = 22;

	/** 白煮鸡后腿 **/
	public static final Integer GOODS_DRUMSTICKS = 23;

	/** 黯然销魂肉  **/
	public static final Integer GOODS_GLOOMY_MEAT = 24;

	/** 金蝉脱壳丸 **/
	public static final Integer GOODS_CICADAS_SHELLING = 25;

	public static void main(String[] args) {
		FileOutputStream fop = null;
		File file;
		String content = " "+"\r\n"+"\r\n";
		try {

			file = new File("C:/Users/Administrator/Desktop/new 1.txt");
			fop = new FileOutputStream(file,true);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			byte[] contentInBytes = content.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
