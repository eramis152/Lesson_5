package com.example.lesson5;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final String TAG = "GestureDetector";
    private GestureDetector gestureDetector;
    private TextView gestureInfo;
    private View gestureArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureInfo = findViewById(R.id.gesture_info);
        gestureArea = findViewById(R.id.gesture_area);
        gestureDetector = new GestureDetector(this, this);
        gestureDetector.setOnDoubleTapListener(this); // Añadir el listener para el doble toque

        // Configurar el listener para el área de detección de gestos
        gestureArea.setOnTouchListener((v, event) -> {
            // Comprobar si el toque está dentro del área del rectángulo
            if (isInsideView(event, gestureArea)) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
            return false; // Ignorar toques fuera del área
        });
    }

    private boolean isInsideView(MotionEvent event, View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        Rect rect = new Rect(location[0], location[1], location[0] + view.getWidth(), location[1] + view.getHeight());
        return rect.contains((int) event.getRawX(), (int) event.getRawY());
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "onShowPress: " + e.toString());
        gestureInfo.setText("Show Press Detected");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp: " + e.toString());
        gestureInfo.setText("Single Tap Detected");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll: " + e1.toString() + " --> " + e2.toString());
        gestureInfo.setText("Scroll Detected");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, "onLongPress: " + e.toString());
        gestureInfo.setText("Long Press Detected");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "onFling: " + e1.toString() + " --> " + e2.toString());
        gestureInfo.setText("Fling Detected");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent motionEvent) {
        return false;
    }

    // Implementar el método para el gesto de doble toque
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(TAG, "onDoubleTap: " + e.toString());
        gestureInfo.setText("Double Tap Detected");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false; // No se necesita manejo adicional
    }
}
