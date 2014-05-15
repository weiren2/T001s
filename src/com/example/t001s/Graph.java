package com.example.t001s;


import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

import com.thanksToGithub.expr.Expr;
import com.thanksToGithub.expr.Parser;
import com.thanksToGithub.expr.SyntaxException;
import com.thanksToGithub.expr.Variable;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Graph extends View
{
	private Paint p;
	private Canvas canvas;
	private int height;
	private int width;
	private String fx;
	private String error = "";
	private boolean isError = false;
	private DecimalFormat format;
	private ArrayList<Float[]> plottedPoints;
	
	public Graph(Context context, AttributeSet aSet)
	{
		super(context, aSet);
		p = new Paint();
		canvas = new Canvas();
		format = new DecimalFormat();
		plottedPoints = new ArrayList<Float[]>();
		fx = "2*x^2";
	}

	@Override
	synchronized public void onDraw(Canvas canvas)
	{
		this.canvas = canvas;
		super.onDraw(canvas);
		
		height = getHeight();
		width = getWidth();
		
		p.setColor(Color.WHITE);
		p.setAlpha(255);
		p.setStrokeWidth(2);
		canvas.drawRect(0,0,getWidth(),getHeight(),p);
		drawPlane();
		
		p.setColor(Color.RED);
		p.setTextSize(30);
		canvas.drawText(error, 10, 25, p);
		
		drawGraph();
		drawPlots();
	}
	
	public void drawPlots()
	{
		p.setColor(Color.RED);
		p.setTextSize(20);
			
		for(int i = 0; i < plottedPoints.size(); i++)
		{
			float x = plottedPoints.get(i)[0];
			float y = plottedPoints.get(i)[1];
			
			float drawX = (x * width / 20) + (width / 2);
			float drawY = (-y * height / 20) + (height / 2);
			
			canvas.drawCircle(drawX,drawY,5,p);
			
			String plot = "(" + format.format(x) + ", " + format.format(y) + ")";
			
			canvas.drawText(plot, drawX, drawY, p);
		}
	}
	
	public void drawPlane()
	{
		p.setColor(Color.BLACK);
		canvas.drawLine(0, height/2, width, height/2, p);
		canvas.drawLine(width/2, 0, width/2, height, p);
			  
		for(int i = -10; i <= 10; i++)
		{
			int x = (width / 2) + (i * width / 20);
			int y1 = (height / 2) - 5;
			int y2 = (height / 2) + 5;
				  
			canvas.drawLine(x, y1, x, y2, p);
		}
		for(int i = -10; i <= 10; i ++)
		{
			int x1 = (width / 2) - 5;
			int x2 = (width / 2) + 5;
			int y = (height / 2) + (i * height / 20);
			
			canvas.drawLine(x1, y, x2, y, p);
		}	  
	}
	
	public void drawGraph()
	{
		Expr expr;
		Variable x = Variable.make("x");
		p.setColor(Color.BLUE);
		if(!isError)
		{
			try {
			    expr = Parser.parse(fx);	    
				
		        for(float i = -10;  i< 10; i+=.01)
			   	{
				   	double x1 = i;
				   	double x2 = i + .01;
				    
				   	//sets value of variable x
				   	//takes the opposite of the y values due to the canvas
				   	//being set to an ascending format and Cartesian plane being descending
				   	x.setValue(x1);
				   	double y1 = -expr.value();
				   	x.setValue(x2);
				    double y2 = -expr.value();
				    
				    //adjusts the values to the canvas size
				    //divide the width/height by 20 due to domain being (-10,10)
				    //add width/height divided by 2 to center function
				    x1 = (x1 * width / 20) + (width / 2);
				    x2 = (x2 * width / 20) + (width / 2);
				    y1 = (y1 * height / 20) + (height / 2);
				    y2 = (y2 * height / 20) + (height / 2);
				   		  
				   	canvas.drawLine((int)x1, (int)y1, (int)x2, (int)y2, p);
			   	}
		        isError = false;
		        postInvalidate();
			} catch (SyntaxException e) {
			    error = (e.explain());
			    isError = true;
			    postInvalidate();
			    return;
			}	
		}
	}
	
	public void setError(String error)
	{
		this.error = error;
	}
	
	public void setFX(String fx)
	{
		this.fx = fx;
		error = "";
		isError = false;
		plottedPoints.clear();
		postInvalidate();
	}
	
	public void getF(float input)
	{
		Expr expr;
		Variable x = Variable.make("x");
		input = (input * 20 / width) - (2 / width) - 10;
		try {
		    expr = Parser.parse(fx);	    
			x.setValue(input);
			float fx = (float) expr.value();
		    
	        error = "(" + format.format(input) + ", " + format.format(fx) + ")";
	        drawGraph();
	        
	        Float[] plots = {input, fx};
	        
	        plottedPoints.add(plots);
	        drawPlots();
		} catch (SyntaxException e) {
		    error = (e.explain());
		    postInvalidate();
		    return;
		}
		postInvalidate();
	}
	
	
}
