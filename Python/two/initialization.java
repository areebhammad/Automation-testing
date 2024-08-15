private void initializeMediaPlayer() {
    if (mediaPlayer == null) {
        mediaPlayer = new MediaPlayer();
        try {
            Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video);
            mediaPlayer.setDataSource(this, videoUri);
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
