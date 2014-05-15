package com.example.t001s;


import android.app.*;
import android.content.Intent;
import android.os.*;
import android.view.*;
import android.view.View.OnTouchListener;
import android.widget.*;

public class GraphMain extends Activity implements OnTouchListener
{
	private String fx;
	private EditText functionEdit;
	private Button functionBtn;
	private Graph graph;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fx = "";
        graph = ((Graph)findViewById(R.id.canvas));
        
        functionBtn = (Button)findViewById(R.id.functionBtn);
        
        functionEdit = (EditText)findViewById(R.id.function);
        
        functionBtn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        fx = functionEdit.getText().toString();
                        graph.setFX(fx);
                    }
                });
        graph.setOnTouchListener(this);
    }
  
    public boolean onTouch(View view, MotionEvent event)
    {
    	int x = (int) event.getX();
		graph.getF(x);
		return false;
	}
    
    
}
