package net.pois0nbread.camera;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Changer implements IXposedHookLoadPackage{


	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable{

		try{

			XposedHelpers.findAndHookMethod("android.hardware.Camera", lpparam.classLoader, "open", 
					new XC_MethodReplacement() {
				@Override
				protected Object replaceHookedMethod(MethodHookParam arg0) throws Throwable {
					return android.hardware.Camera.open(1);
				}
			});
			XposedHelpers.findAndHookMethod("android.hardware.Camera", lpparam.classLoader, "open",
					int.class , new XC_MethodHook() {
				@Override
				protected void beforeHookedMethod(MethodHookParam param)
						throws Throwable {
					param.args[0] = 1;
				}
			});
		} catch (Throwable e){
			e.printStackTrace();
		}
	}

}
