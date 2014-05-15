package com.example.t001s;

import android.os.Bundle;
import android.view.View;

import java.util.Calendar;
import java.util.Locale;






import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

	public class MainActivity extends Activity implements OnInitListener {

	private Button speakBtn;
	private static final int REQ_TTS_STATUS_CHECK = 0;
	private static final String TAG = "TTS";
	private TextToSpeech tts;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.fragment_main);
		Button calculator=(Button)findViewById(R.id.btn_2);
		

		
		calculator.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent=new Intent();
				//intent.setClass(MainActivity.this,Calculator.class);
				//startActivity(intent);
				//finish();
				MainActivity.this.startActivity(new Intent (MainActivity.this, Calculator.class) );  
			}
		});
		
Button graph=(Button) findViewById(R.id.btn_3);
		
		graph.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this,GraphMain.class);
				MainActivity.this.startActivity(intent);
				//finish();
			}
		});
		
		

	Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
	startActivityForResult(checkIntent, REQ_TTS_STATUS_CHECK);


		speakBtn = (Button) findViewById(R.id.button1);

		speakBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Calendar cld = Calendar.getInstance();
				int minute = cld.get(Calendar.MINUTE);
				int hour = cld.get(Calendar.HOUR_OF_DAY);
				
				String speakTime = "";
				String post = "";

				post = hour < 12 ? "AM" : "PM";
				hour = hour == 12 ? 12 : hour % 12;

				if (minute == 0) {
					speakTime = hour + "o'clock ";
				} else if (minute > 0 && minute <= 10) {
					speakTime = minute + " past" + hour;
				} else if (minute == 15) {
					speakTime = "a quarter past " + hour;
				} else if (minute == 45) {
					if (cld.get(Calendar.HOUR_OF_DAY) == 23)
						speakTime = hour + " " + minute;
					else
						speakTime = "a quarter to " + ((hour) + 1);
				} else {
					speakTime = hour + " " + minute;
				}

				tts.speak("It's " + speakTime + " " + post,
						TextToSpeech.QUEUE_ADD, null);

			}
		});
		
		
	
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS) {
			int result = tts.setLanguage(Locale.US);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED)

			{
				Log.v(TAG, "Language is not available");
				speakBtn.setEnabled(false);
			} else {
				// mTts.speak("This is an example of speech synthesis.",
				// TextToSpeech.QUEUE_ADD, null);
				speakBtn.setEnabled(true);
			}
		}

	}

	@SuppressWarnings("deprecation")
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQ_TTS_STATUS_CHECK) {
			switch (resultCode) {
			case TextToSpeech.Engine.CHECK_VOICE_DATA_PASS:

			{
				tts = new TextToSpeech(this, this);
				Log.v(TAG, "TTS Engine is installed!");

			}

				break;
			case TextToSpeech.Engine.CHECK_VOICE_DATA_BAD_DATA:

			case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_DATA:

			case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_VOLUME:

			{

				Log.v(TAG, "Need language stuff:" + resultCode);
				Intent dataIntent = new Intent();
				dataIntent
						.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(dataIntent);

			}
				break;
			case TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL:

			default:
				Log.v(TAG, "Got a failure. TTS apparently not available");
				break;
			}
		} else {

		}
	}

	protected void onPause() {
		super.onPause();
		if (tts != null)
			tts.stop();
	}
	  protected void onStop() {
	        super.onStop();
			if (tts != null)
				tts.stop();
	    }
	  
	    protected void onResume() {
	        super.onResume();
	    }

	protected void onDestroy() {
		super.onDestroy();
		tts.shutdown();
	}

}