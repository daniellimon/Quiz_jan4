package com.quiz.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 */

public class UtilityManager {

    private Activity mActivity;
    private ProgressDialog mProgressDialog;

    public UtilityManager(Activity activity) {
        this.mActivity = activity;
    }

    public UtilityManager(Context context) {
        this.mActivity = (Activity) context;
    }

    public String getDeviceId() {
        return Settings.Secure.getString(mActivity.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public void showMessage(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param message
     * @param isCancelable
     */
    public void showProgressDialog(String message, boolean isCancelable) {
        mProgressDialog = new ProgressDialog(mActivity);
        mProgressDialog.setMessage(message);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setCancelable(isCancelable);
        mProgressDialog.setInverseBackgroundForced(false);
        mProgressDialog.setCanceledOnTouchOutside(isCancelable);
        mProgressDialog.show();
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    /**
     * @param title
     * @param message
     * @param positiveButton
     */
    public void showAlertDialog(String title, String message, String positiveButton) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    /**
     * @param title
     * @param message
     * @param positiveButton
     */
    public void showAlertDialog(String title, Spanned message, String positiveButton) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.show();
    }

    /**
     * @param title
     * @param message
     * @param positiveButton
     * @param mAlertDialogListener
     */
    public void showAlertDialog(String title, Spanned message, String positiveButton, final AlertDialogListener mAlertDialogListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialogListener.onPositiveButtonClick(dialog, which);
            }
        });
        alertDialog.show();
    }

    /**
     * @param title
     * @param message
     * @param positiveButton
     * @param mAlertDialogListener
     */
    public void showAlertDialog(String title, String message, String positiveButton, final AlertDialogListener mAlertDialogListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialogListener.onPositiveButtonClick(dialog, which);
            }
        });
        alertDialog.show();
    }

    public void showAlertDialog(String title, String message, String positiveButton, final AlertDialogListener mAlertDialogListener, boolean isCancelled) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity);
        alertDialog.setTitle(title);
        alertDialog.setCancelable(isCancelled);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialogListener.onPositiveButtonClick(dialog, which);
            }
        });
        alertDialog.show();
    }



    public interface AlertDialogListener {
        public void onPositiveButtonClick(DialogInterface dialog, int which);
    }


    /**
     * @param title
     * @param message
     * @param positiveButtonName
     * @param negativeButtonName
     */
    public void showConfirmDialog(String title, String message, String positiveButtonName, String negativeButtonName) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setNegativeButton(negativeButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    /**
     * @param title
     * @param message
     * @param positiveButtonName
     * @param negativeButtonName
     * @param mDialogListener
     */
    public void showConfirmDialog(String title, String message, String positiveButtonName, String negativeButtonName, final ConfirmDialogListener mDialogListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDialogListener.onPositiveButtonClick(dialog, which);
            }
        });
        alertDialog.setNegativeButton(negativeButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDialogListener.onNegativeButtonClick(dialog, which);
            }
        });
        alertDialog.show();
    }

    /**
     * @param title
     * @param message
     * @param positiveButtonName
     * @param negativeButtonName
     * @param mDialogListener
     */
    public void showConfirmDialog(String title, Spanned message, String positiveButtonName, String negativeButtonName, final ConfirmDialogListener mDialogListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDialogListener.onPositiveButtonClick(dialog, which);
            }
        });
        alertDialog.setNegativeButton(negativeButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDialogListener.onNegativeButtonClick(dialog, which);
            }
        });
        alertDialog.show();
    }

    public interface ConfirmDialogListener {
        public void onPositiveButtonClick(DialogInterface dialog, int which);

        public void onNegativeButtonClick(DialogInterface dialog, int which);
    }

    public void hideSoftKeyboard() {
        if (mActivity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * @param inputStr
     * @return
     */
    public boolean isValidateNumber(CharSequence inputStr) {
        String expression = "^[0-9-+]{9,15}$";
        return Pattern.compile(expression, Pattern.CASE_INSENSITIVE).matcher(inputStr).matches();
    }

    /**
     * @param mView
     * @return
     */
    public boolean isValidText(View mView) {
        if (mView != null && mView instanceof EditText) {
            final EditText box = (EditText) mView;
            return (box.getText() != null && (box.getText().toString()).trim() != null && box.getText().toString().trim().length() > 0);
        } else if (mView != null && mView instanceof TextView) {
            final TextView box = (TextView) mView;
            return (box.getText() != null && box.getText().toString().trim() != null && box.getText().toString().trim().length() > 0);
        } else if (mView != null && mView instanceof Spinner) {
            final Spinner box = (Spinner) mView;
            return (box.getSelectedItem() != null && box.getSelectedItem().toString().trim() != null && box.getSelectedItem().toString().trim().length() > 0);
        }
        return false;
    }

    /**
     * @param mEditText
     */
    public void validateInput(EditText mEditText) {
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        mEditText.setFilters(new InputFilter[]{filter});
    }

    /**
     * @param target
     * @return
     */
    public final boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;
        else
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * @param mView
     * @return
     */
    public static boolean isValidEmail(View mView) {
        String regExpn = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
        Pattern patternObj = Pattern.compile(regExpn);
        Matcher matcherObj = patternObj.matcher((((EditText) mView).getText().toString()).trim());
        if (matcherObj.matches())
            return true;
        else
            return false;
    }

    public int getColor(int id) {
        if (Build.VERSION.SDK_INT >= 23) {
            return ContextCompat.getColor(mActivity, id);
        } else {
            return mActivity.getResources().getColor(id);
        }
    }

    /**
     * @param TO
     * @param CC
     * @param Subject
     * @param Body
     * @param filePaths
     */
    public void sendMailFromGmail(String TO, String CC, String Subject, String Body, ArrayList<String> filePaths) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        emailIntent.setType("message/rfc822");
        emailIntent.setData(Uri.parse(TO.toLowerCase().trim()));
        emailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{TO});
        emailIntent.putExtra(Intent.EXTRA_CC, new String[]{CC});
        ArrayList<Uri> uris = new ArrayList<Uri>();
        // convert from paths to Android friendly Parcelable Uri's
        if (filePaths != null) {

            for (String file : filePaths) {
                File fileIn = new File(file);
                Uri u = Uri.fromFile(fileIn);
                uris.add(u);
            }
        }
        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, Subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, Body);
        try {
            mActivity.startActivity(emailIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param value
     * @param format
     * @return
     */
    public static String formatDecimal(String value, String format) {
        String input = value.replaceAll(",", "");
        DecimalFormat mDecimalFormat = new DecimalFormat(format);
        return mDecimalFormat.format(Double.valueOf(input));
    }

    /**
     * @param view
     */
    public void expand(final View view) {
        final int targetHeight = view.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with animation height of 0.
        view.getLayoutParams().height = 1;
        view.setVisibility(View.VISIBLE);
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view.getLayoutParams().height = interpolatedTime == 1
                        ? WindowManager.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                view.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        animation.setDuration((int) (targetHeight / view.getContext().getResources().getDisplayMetrics().density));
        view.startAnimation(animation);
    }

    /**
     * @param view
     */
    public void collapse(final View view) {
        final int initialHeight = view.getMeasuredHeight();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        animation.setDuration((int) (initialHeight / view.getContext().getResources().getDisplayMetrics().density));
        view.startAnimation(animation);
    }

    /**
     * @param view
     * @param delay
     * @param duration
     */
    public void animate(View view, long delay, long duration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            view.setScaleX(0);
            view.setScaleY(0);
            view.animate()
                    .scaleX(1)
                    .scaleY(1)
                    .setDuration(duration)
                    .setStartDelay(delay)
                    .setInterpolator(new OvershootInterpolator())
                    .start();
        }
    }

    public boolean checkInternetConnection() {
        boolean isConnected = false;
        ConnectivityManager _connManager = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = _connManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            isConnected = networkInfo.isConnectedOrConnecting();
        }
        return isConnected;
    }
}
