package theblackdiamonds.com.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import theblackdiamonds.com.R;
import theblackdiamonds.com.activities.GameOverActivity;

/**
 * Created by Kush Pandya on 8/19/2019.
 */
public class FlyingFishView extends View {

    int score, canvasWidth, canvasHeight, lifeCounterOfFish;
    int yellowX, yellowY, yellowSpeed = 16;
    int greenX, greenY, greenSpeed = 20;
    int redX, redY, redSpeed = 25;
    private Bitmap[] fish = new Bitmap[2];
    private Bitmap backgroundImage;
    private Bitmap[] life = new Bitmap[2];
    private Paint scorerPaint = new Paint();
    private int fishX = 10, fishY, fishSpeed;
    private Paint yellowPaint = new Paint();
    private Paint greenPaint = new Paint();
    private Paint redPaint = new Paint();

    private boolean isTouch = false;

    public FlyingFishView(Context context) {
        super(context);
        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);

        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        scorerPaint.setColor(Color.WHITE);
        scorerPaint.setTextSize(70);
        scorerPaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorerPaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);

        fishY = 550;
        score = 0;
        lifeCounterOfFish = 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasHeight = getHeight();
        canvasWidth = getWidth();

        canvas.drawBitmap(backgroundImage, 0, 0, null);

        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHeight - minFishY * 3;
        fishY = fishY + fishSpeed;

        if (fishY < minFishY) {
            fishY = minFishY;
        }
        if (fishY > maxFishY) {
            fishY = maxFishY;
        }

        fishSpeed = fishSpeed + 2;

        if (isTouch) {
            canvas.drawBitmap(fish[1], fishX, fishY, null);
            isTouch = false;
        } else {
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }

        yellowX = yellowX - yellowSpeed;

        if (isHit(yellowX, yellowY)) {
            score = score + 10;
            yellowX = -100;
        }
        if (yellowX < 0) {
            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(yellowX, yellowY, 25, yellowPaint);

        greenX = greenX - greenSpeed;

        if (isHit(greenX, greenY)) {
            score = score + 20;
            greenX = -100;
        }
        if (greenX < 0) {
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(greenX, greenY, 25, greenPaint);

        redX = redX - redSpeed;

        if (isHit(redX, redY)) {
            redX = -100;
            lifeCounterOfFish--;

            if (lifeCounterOfFish == 0) {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
                Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(gameOverIntent);
            }
        }
        if (redX < 0) {
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(redX, redY, 30, redPaint);

        canvas.drawText("Score : " + score, 20, 60, scorerPaint);

        for (int i = 0; i < 3; i++) {
            int x = (int) (580 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if (i < lifeCounterOfFish) {
                canvas.drawBitmap(life[0], x, y, null);
            } else {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }
    }

    public boolean isHit(int x, int y) {
        return fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            isTouch = true;
            fishSpeed = -22;
        }
        return true;
    }
}