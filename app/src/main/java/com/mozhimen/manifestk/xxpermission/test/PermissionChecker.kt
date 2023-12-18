package com.mozhimen.manifestk.xxpermission.test

import android.content.Context
import com.mozhimen.basick.elemk.commons.I_Listener
import com.mozhimen.manifestk.xxpermission.XXPermissionUtil

/**
 * @ClassName PermissionChecker
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/12/5 16:21
 * @Version 1.0
 */
object PermissionChecker {
    @JvmStatic
    fun startPermissions(context: Context, allGrant: I_Listener? = null) {
        startPermissionInstall(context) {
            startPermissionReadWrite(context) {
                allGrant?.invoke()
            }
        }
    }

    @JvmStatic
    fun startPermissionInstall(context: Context, allGrant: I_Listener? = null) {
        if (XXPermissionUtil.hasInstallPermission(context)) {
            allGrant?.invoke()
        } else {
//            showDialogInstallPermission(context) {
                XXPermissionUtil.requestInstallPermission(context,
                    onGranted = {
                        allGrant?.invoke()
                    },
                    onDenied = {
                        XXPermissionUtil.startSettingInstall(context)
                    }
                )
//            }
        }
    }

    @JvmStatic
    fun startPermissionReadWrite(context: Context, allGrant: I_Listener? = null) {
        if (XXPermissionUtil.hasReadWritePermission(context)) {
            allGrant?.invoke()
        } else {
//            showDialogReadWritePermission(context) {
                XXPermissionUtil.requestReadWritePermission(context,
                    onGranted = {
                        allGrant?.invoke()
                    },
                    onDenied = {
                        XXPermissionUtil.startSettingManageStorage(context)
                    }
                )
//            }
        }
    }

//    ///////////////////////////////////////////////////////////////////////////
//
//    private fun showDialogInstallPermission(context: Context, onGenerate: I_Listener) {
//        showTipBtnDialog(context, context.getString(com.ty.lelejoy.module_common.R.string.str_permission_install), onGenerate)
//    }
//
//    private fun showDialogReadWritePermission(context: Context, onGenerate: I_Listener) {
//        showTipBtnDialog(context, context.getString(com.ty.lelejoy.module_common.R.string.str_permission_internal), onGenerate)
//    }
//
//    private fun showDialogRestart(context: Context, onGenerate: I_Listener) {
//        showTipBtnNoCloseDialog(context, context.getString(com.ty.lelejoy.module_common.R.string.str_Access_Granted_Please_restart_app_now), onGenerate)
//    }
//
//    ///////////////////////////////////////////////////////////////////////////
//
//    private fun showTipBtnDialog(
//        context: Context,
//        content: String,
//        block: I_Listener,
//        title: String = context.getString(com.ty.lelejoy.module_common.R.string.str_Permission_Alert),
//        btnTxt: String = context.getString(com.ty.lelejoy.module_common.R.string.str_Go_to_Authorize)
//    ) {
//        DialogTipBtn(context, title, content, btnTxt, block).show()
//    }
//
//    private fun showTipBtnNoCloseDialog(
//        context: Context,
//        content: String,
//        block: I_Listener,
//        btnTxt: String = context.getString(com.ty.lelejoy.module_common.R.string.str_Restart_Now)
//    ) {
//        DialogTipBtnNoClose(context, content, btnTxt, block).show()
//    }
}