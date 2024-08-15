package com.devskiller.videoautoplayback;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle; 
import android.view.Surface;
import android.view.TextureView;
import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final class VideoSurfaceTextureListener implements TextureView. SurfaceTextureListener {
        private final WeakReference<MainActivity> weakParent;

        private VideoSurfaceTextureListener(MainActivity parent) { 
            weakParent = new WeakReference<>(parent);
        }

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            MainActivity parent weakParent.get();
            if (parent != null) {
                //START CHANGES
                //END CUANCES
            }
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
        }

        @Override
        public boolean onSurfaceTextureDestroyed (SurfaceTexture surfaceTexture) {
            MainActivity parent weakParent.get(); 
            if (parent != null) {
                parent.weakVideoSurfaceTexture.clear();
            }
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated (SurfaceTexture surfaceTexture) {
        }
    }

    MediaPlayer mediaPlayer;
    private WeakReference<SurfaceTexture> weakVideoSurfaceTexture = new WeakReference<>(null);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //START CHANGES
        //END CHANGES
    }

    @Override
    protected void onStop() {
    //START CHANGES
    //END CHANGES
    super.onStop();
    }

    private void setSurfaceTextureAndPlay(@NonNull SurfaceTexture surfaceTexture) {
        mediaPlayer.setSurface(new Surface (surfaceTexture));
        mediaPlayer.start();
}
}