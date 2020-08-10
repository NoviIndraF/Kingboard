package com.nifcompany.kingboard.keyboard;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.InputMethodSubtype;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nifcompany.kingboard.R;
import com.nifcompany.kingboard.database.DatabaseHelper;
import com.nifcompany.kingboard.database.DatabaseManager;
import com.nifcompany.kingboard.json.post.PostText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class LatinKeyboardView extends KeyboardView {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    static final int KEYCODE_OPTIONS = -100;
    static final int KEYCODE_LANGUAGE_SWITCH = -101;
    List<PostText> listPostText = new ArrayList<>();
    DatabaseHelper dbHelper;
    DatabaseManager dbManager;
    ArrayList<HashMap<String,String>> arrayListData;
    private Cursor cursor;

    public LatinKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LatinKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean onLongPress(Key key) {

        // For English keyboard.
        if (key.codes[0] == Keyboard.KEYCODE_CANCEL) {
            getOnKeyboardActionListener().onKey(KEYCODE_OPTIONS, null);
            return true;
        } else if (key.codes[0] == '0') {
            getOnKeyboardActionListener().onKey('+', null);
            return true;
        } else if (key.codes[0] == 'q' || key.codes[0] == 'Q' || key.codes[0] == '۱') {
            getOnKeyboardActionListener().onKey('1', null);
            return true;
        } else if (((key.codes[0] == 'w' || key.codes[0] == 'W') && SoftKeyboard.mActiveKeyboard == "en_US") || key.codes[0] == '۲') {
            getOnKeyboardActionListener().onKey('2', null);
            return true;
        } else if (key.codes[0] == 'e' || key.codes[0] == 'E' || key.codes[0] == '۳') {
            getOnKeyboardActionListener().onKey('3', null);
            return true;
        } else if (((key.codes[0] == 'r' || key.codes[0] == 'R') && SoftKeyboard.mActiveKeyboard == "en_US") || key.codes[0] == '۴') {
            getOnKeyboardActionListener().onKey('4', null);
            return true;
        } else if (((key.codes[0] == 't' || key.codes[0] == 'T') && SoftKeyboard.mActiveKeyboard == "en_US") || key.codes[0] == '۵') {
            getOnKeyboardActionListener().onKey('5', null);
            return true;
        } else if (key.codes[0] == 'y' || key.codes[0] == 'Y' || key.codes[0] == '۶') {
            getOnKeyboardActionListener().onKey('6', null);
            return true;
        } else if (key.codes[0] == 'u' || key.codes[0] == 'U' || key.codes[0] == '۷') {
            getOnKeyboardActionListener().onKey('7', null);
            return true;
        } else if (key.codes[0] == 'i' || key.codes[0] == 'I' || key.codes[0] == '۸') {
            getOnKeyboardActionListener().onKey('8', null);
            return true;
        } else if (key.codes[0] == 'o' || key.codes[0] == 'O' || key.codes[0] == '۹') {
            getOnKeyboardActionListener().onKey('9', null);
            return true;
        } else if (key.codes[0] == 'p' || key.codes[0] == 'P' || key.codes[0] == '۰') {
            getOnKeyboardActionListener().onKey('0', null);
            return true;
        } else if ((key.codes[0] == 'S' || key.codes[0] == 's') && SoftKeyboard.mActiveKeyboard == "en_US") {
            getOnKeyboardActionListener().onKey('ϐ', null);
            return true;

            // For Farsi Keyboard
        } else if (SoftKeyboard.mActiveKeyboard == "fa_AF" && key.codes[0] == 'ض') {
            getOnKeyboardActionListener().onKey('۱', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "fa_AF" && key.codes[0] == 'ص') {
            getOnKeyboardActionListener().onKey('۲', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "fa_AF" && key.codes[0] == 'ث') {
            getOnKeyboardActionListener().onKey('۳', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "fa_AF" && key.codes[0] == 'ق') {
            getOnKeyboardActionListener().onKey('۴', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "fa_AF" && key.codes[0] == 'ف') {
            getOnKeyboardActionListener().onKey('۵', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "fa_AF" && key.codes[0] == 'غ') {
            getOnKeyboardActionListener().onKey('۶', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "fa_AF" && key.codes[0] == 'ع') {
            getOnKeyboardActionListener().onKey('۷', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "fa_AF" && key.codes[0] == 'ه') {
            getOnKeyboardActionListener().onKey('۸', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "fa_AF" && key.codes[0] == 'خ') {
            getOnKeyboardActionListener().onKey('۹', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "fa_AF" && key.codes[0] == 'ح') {
            getOnKeyboardActionListener().onKey('۰', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "fa_AF" && key.codes[0] == 'ش') {
            getOnKeyboardActionListener().onKey('ٴ', null);
            return true;

            // For Pashto Keyboard
        } else if (Objects.equals(SoftKeyboard.mActiveKeyboard, "ps_AF") && key.codes[0] == 'ث') {
            getOnKeyboardActionListener().onKey('۲', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "ps_AF" && key.codes[0] == 'ق') {
            getOnKeyboardActionListener().onKey('۳', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "ps_AF" && key.codes[0] == 'ف') {
            getOnKeyboardActionListener().onKey('۴', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "ps_AF" && key.codes[0] == 'ځ') {
            getOnKeyboardActionListener().onKey('۵', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "ps_AF" && key.codes[0] == 'څ') {
            getOnKeyboardActionListener().onKey('۶', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "ps_AF" && key.codes[0] == 'خ') {
            getOnKeyboardActionListener().onKey('۷', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "ps_AF" && key.codes[0] == 'ح') {
            getOnKeyboardActionListener().onKey('۸', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "ps_AF" && key.codes[0] == 'چ') {
            getOnKeyboardActionListener().onKey('۹', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "ps_AF" && key.codes[0] == 'ج') {
            getOnKeyboardActionListener().onKey('۰', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "ps_AF" && key.codes[0] == 'ن') {
            getOnKeyboardActionListener().onKey('ڼ', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "ps_AF" && key.codes[0] == 'و') {
            getOnKeyboardActionListener().onKey('ؤ', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "ps_AF" && key.codes[0] == 'ط') {
            getOnKeyboardActionListener().onKey('ظ', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard == "ps_AF" && key.codes[0] == 'ټ') {
            getOnKeyboardActionListener().onKey('ة', null);
            return true;

            // For Latin Keys
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && (key.codes[0] == 'w' || key.codes[0] == 'W')) {
            getOnKeyboardActionListener().onKey('1', null);
            return true;
        } else if (key.codes[0] == 'ə' || key.codes[0] == 'Ə') {
            getOnKeyboardActionListener().onKey('2', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 's') {
            getOnKeyboardActionListener().onKey('š', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'd') {
            getOnKeyboardActionListener().onKey('ḍ', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'z') {
            getOnKeyboardActionListener().onKey('ž', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'c') {
            getOnKeyboardActionListener().onKey('č', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'n') {
            getOnKeyboardActionListener().onKey('ṇ', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'g') {
            getOnKeyboardActionListener().onKey('ǧ', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'l') {
            getOnKeyboardActionListener().onKey('ɣ', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'j') {
            getOnKeyboardActionListener().onKey('ǰ', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'S') {
            getOnKeyboardActionListener().onKey('Š', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'D') {
            getOnKeyboardActionListener().onKey('Ḍ', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'Z') {
            getOnKeyboardActionListener().onKey('Ž', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'C') {
            getOnKeyboardActionListener().onKey('Č', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'N') {
            getOnKeyboardActionListener().onKey('Ṇ', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'G') {
            getOnKeyboardActionListener().onKey('Ǧ', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'L') {
            getOnKeyboardActionListener().onKey('Ɣ', null);
            return true;
        } else if (SoftKeyboard.mActiveKeyboard != "en_US" && key.codes[0] == 'J') {
            getOnKeyboardActionListener().onKey('ǰ', null);
            return true;
        } else {
            return super.onLongPress(key);
        }
    }

    void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final LatinKeyboard keyboard = (LatinKeyboard) getKeyboard();
        // keyboard.setSpaceIcon(getResources().getDrawable(subtype.getIconResId()));
        invalidateAllKeys();
    }

    Paint paint = new Paint();
    Paint paint2 = new Paint();

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        ambilData(listPostText, canvas);

    }

    private void drawKey(Canvas canvas, List<PostText> listPostText) {

    }

    private void ambilData(List<PostText> listPostText2, Canvas canvas) {
//        Log.d("TAG : ", "LIST : ");
//        Query allLastQuery = databaseReference.child("message").orderByKey().limitToLast(3);
//        allLastQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                listPostText2.clear();
//
//                for(DataSnapshot child : dataSnapshot.getChildren()) {
//                    Log.w("ID Data", ""+child.getKey());
//                    String id = child.getKey();
//                    String judul = child.child("judul").getValue().toString();
//                    String text = child.child("text").getValue().toString();
//
//                    listPostText2.add(new PostText(id, judul, text));
//                    Log.d("TAG : ", "LIST : "+listPostText2.size());
//                }
//
//                drawKey(canvas, listPostText2);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        });
        dbManager = new DatabaseManager(getContext());


        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(getResources().getDimension(R.dimen.canvasTextSize));
        int keyYLocation = getResources().getDimensionPixelSize(R.dimen.canvasKeyY);
        paint.setColor(getResources().getColor(R.color.white));

        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setTextSize(36);
        paint2.setColor(getResources().getColor(R.color.white));

//        Log.d("DrawKey", "listPostText : "+ listPostText.toString());
//        Log.d("DrawKey", "isiPostText 1 : "+ listPostText.get(0).getJudul().substring(10));
//        Log.d("DrawKey", "isiPostText 2 : "+  listPostText.get(1).getJudul().substring(10));
//        Log.d("DrawKey", "isiPostText 3 : "+ listPostText.get(2).getJudul().substring(10));
//        Log.d("DrawKey", "canvas : "+canvas.toString());
//        Log.d("DrawKey", "keyYLocation : "+keyYLocation);
//        Log.d("DrawKey", "paint : "+paint.toString());
//        Log.d("DrawKey", "paint2 : "+paint2.toString());

        List<Key> keys = getKeyboard().getKeys();

        Log.d("DrawKey", "keys : "+keys.toString());

        for (Key key : keys) {
            Log.d("DrawKey", "key : "+key.toString());
            if (key.codes[0] == 5001) {
                canvas.drawText("listPostText.get(0).getJudul()", key.x + (key.width / 2) + 10, key.y + keyYLocation + 60, paint2);
                Log.d("TAG", "Kata 1 : " );}

            else if (key.codes[0] == 5002){
                canvas.drawText("listPostText.get(1).getJudul()", key.x + (key.width / 2) + 10, key.y + keyYLocation + 60, paint2);
                Log.d("TAG", "Kata 2 : " );}

            else if (key.codes[0] == 5003) {
                canvas.drawText("listPostText.get(2).getJudul()", key.x + (key.width / 2) + 10, key.y + keyYLocation + 60, paint2);
                Log.d("TAG", "Kata 3 : " );}
        }
    }
}