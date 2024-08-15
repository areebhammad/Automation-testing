package com.devskiller.videoautoplayback;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private static final class VideoSurfaceTextureListener implements TextureView.SurfaceTextureListener {
        private final WeakReference<MainActivity> weakParent;

        private VideoSurfaceTextureListener(MainActivity parent) {
            weakParent = new WeakReference<>(parent);
        }

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            MainActivity parent = weakParent.get();
            if (parent != null) {
                parent.weakVideoSurfaceTexture = new WeakReference<>(surface);
                parent.initializeMediaPlayer();  // Ensure this method is defined
                parent.setSurfaceTextureAndPlay(surface);
            }
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            MainActivity parent = weakParent.get();
            if (parent != null) {
                parent.weakVideoSurfaceTexture.clear();
            }
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    MediaPlayer mediaPlayer;
    private WeakReference<SurfaceTexture> weakVideoSurfaceTexture = new WeakReference<>(null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextureView textureView = findViewById(R.id.textureView);
        textureView.setSurfaceTextureListener(new VideoSurfaceTextureListener(this));

        // Prevent screen from turning off while playing the video
        getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SurfaceTexture surfaceTexture = weakVideoSurfaceTexture.get();
        if (surfaceTexture != null) {
            setSurfaceTextureAndPlay(surfaceTexture);
        }
    }

    @Override
    protected void onStop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        // Release resources if activity is stopped
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onStop();
    }

    // Ensure this method is present in MainActivity
    private void initializeMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            try {
                Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video); // Replace 'sample_video' with your actual file name
                mediaPlayer.setDataSource(this, videoUri);
                mediaPlayer.setLooping(true);
                mediaPlayer.prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setSurfaceTextureAndPlay(@NonNull SurfaceTexture surfaceTexture) {
        if (mediaPlayer != null) {
            mediaPlayer.setSurface(new Surface(surfaceTexture));
            mediaPlayer.start();
        }
    }
}
