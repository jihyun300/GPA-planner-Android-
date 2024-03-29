/*
 * 	   Created by Daniel Nadeau
 * 	   daniel.nadeau01@gmail.com
 * 	   danielnadeau.blogspot.com
 * 
 * 	   Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.example.holographylibrary;

import android.content.Context;
import android.graphics.*;
import android.graphics.Bitmap.Config;
import android.graphics.Path.Direction;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import com.example.gpaplan_mainpage.GroupItem;
import com.example.gpaplan_mainpage.R;

public class LineGraph extends View {
	
	private ArrayList<Line> lines = new ArrayList<Line>();
	private ArrayList<GroupItem> grouplist ; 
	private Paint paint = new Paint();
	private Paint txtPaint = new Paint();
	private float minY = 0, minX = 0;
	private float maxY = 0, maxX = 0;
	private boolean isMaxYUserSet = false;
	private int lineToFill = -1;
	private int indexSelected = -1;
	
	private OnPointClickedListener listener;
	private Bitmap fullImage;
	private boolean shouldUpdate = false;
	private boolean showVerticalGrid = true;
    private boolean showMinAndMax = false;
    private boolean showHorizontalGrid = false;
	private int gridColor = 0xCCCCCC;
	
	public LineGraph(Context context){
		this(context,null);
	}
	public LineGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
		txtPaint.setColor(getResources().getColor(R.color.font_color_black));
		txtPaint.setTextSize(30);
		txtPaint.setAntiAlias(true);
	}
	public void setGridColor(int color)
	{
		gridColor = color;
	}
	public void showHorizontalGrid(boolean show)
	{
		showHorizontalGrid = show;
	}
	public void showMinAndMaxValues(boolean show)
	{
        showMinAndMax = show;
    }
	public void setTextColor(int color)
	{
		txtPaint.setColor(color);
	}
	public void setTextSize(float s)
	{
		txtPaint.setTextSize(s);
	}
	public void setMinY(float minY){
		this.minY = minY;
	}
	public void setGrouplist(ArrayList<GroupItem> gr){
		this.grouplist=gr;
	}
	public void update()
	{
		shouldUpdate = true;
		postInvalidate();
	}
	public void removeAllLines(){
		while (lines.size() > 0){
			lines.remove(0);
		}
		shouldUpdate = true;
		postInvalidate();
	}
	
	public void addLine(Line line) {
		lines.add(line);
		shouldUpdate = true;
		postInvalidate();
	}
	public ArrayList<Line> getLines() {
		return lines;
	}
	public void setLineToFill(int indexOfLine) {
		this.lineToFill = indexOfLine;
		shouldUpdate = true;
		postInvalidate();
	}
	public int getLineToFill(){
		return lineToFill;
	}
	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}
	public Line getLine(int index) {
		return lines.get(index);
	}
	public int getSize(){
		return lines.size();
	}
	
	public void setRangeY(float min, float max) {
		minY = min;
		maxY = max;
		isMaxYUserSet = true;
	}
	public float getMaxY(){
		if (isMaxYUserSet){
			return maxY;
		} else {
			maxY = lines.get(0).getPoint(0).getY();
			for (Line line : lines){
				for (LinePoint point : line.getPoints()){
					if (point.getY() > maxY){
						maxY = point.getY();
					}
				}
			}
			return maxY;
		}
		
	}
	public float getMinY(){
		if (isMaxYUserSet){
			return minY;
		} else {
			float min = lines.get(0).getPoint(0).getY();
			for (Line line : lines){
				for (LinePoint point : line.getPoints()){
					if (point.getY() < min) min = point.getY();
				}
			}
			minY = min;
			return minY;
		}
	}
	public float getMaxX(){
			float max = lines.get(0).getPoint(0).getX();
		for (Line line : lines){
			for (LinePoint point : line.getPoints()){
				if (point.getX() > max) max = point.getX();
			}
		}
		maxX = max;
	
		return maxX;
		
	}
	public float getMinX(){
		float max = lines.get(0).getPoint(0).getX();
		for (Line line : lines){
			for (LinePoint point : line.getPoints()){
				if (point.getX() < max) max = point.getX();
			}
		}
		maxX = max;
		return maxX;
	}
	
	public void onDraw(Canvas ca) {
		if (fullImage == null || shouldUpdate) {
			fullImage = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
			Canvas canvas = new Canvas(fullImage);
			String max = (float)maxY+"";// used to display max
			String min = (float)minY+"";// used to display min
			paint.reset();
			Path path = new Path();
			
			float bottomPadding = 30, topPadding = 15;
			float sidePadding = 20;
            if (this.showMinAndMax)
                sidePadding = txtPaint.measureText(max);
			
			float usableHeight = getHeight() - bottomPadding - topPadding;
			float usableWidth = getWidth() - sidePadding;
			float lineSpace = (usableHeight-(usableHeight/9))/4;
			Paint pp= new Paint();
			pp.setColor(getResources().getColor(R.color.child_white));
			pp.setAlpha(255);
			pp.setStyle(Paint.Style.FILL);
			ca.drawRect(sidePadding,0, getWidth(),getHeight()-bottomPadding-topPadding, pp);
			int lineCount = 0;
			for (Line line : lines){
				int count = 0;
                float lastXPixels = 0, newYPixels;
                float lastYPixels = 0, newXPixels;
                float maxY = getMaxY();
				float minY = getMinY();
				float maxX = getMaxX();
				float minX = getMinX();
				
				if (lineCount == lineToFill){
					paint.setColor(Color.BLACK);
					paint.setAlpha(30);
					paint.setStrokeWidth(2);
					for (int i = 10; i-getWidth() < getHeight(); i = i+20){
						canvas.drawLine(i, getHeight()-bottomPadding, 0, getHeight()-bottomPadding-i, paint);
					}
					
					paint.reset();
					
					paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.CLEAR));
					for (LinePoint p : line.getPoints()){
						float yPercent = (p.getY()-minY)/(maxY - minY);
						float xPercent = (p.getX()-minX)/(maxX - minX);
						if (count == 0){
							lastXPixels = sidePadding + (xPercent*usableWidth);
							lastYPixels = getHeight() - bottomPadding -topPadding- (usableHeight*yPercent);
							path.moveTo(lastXPixels, lastYPixels);
						} else {
							newXPixels = sidePadding + (xPercent*usableWidth);
							newYPixels = getHeight() - bottomPadding - topPadding-(usableHeight*yPercent);
							path.lineTo(newXPixels, newYPixels);
							Path pa = new Path();
							pa.moveTo(lastXPixels, lastYPixels);
							pa.lineTo(newXPixels, newYPixels);
							pa.lineTo(newXPixels, 0);
							pa.lineTo(lastXPixels, 0);
							pa.close();
							canvas.drawPath(pa, paint);
							lastXPixels = newXPixels;
							lastYPixels = newYPixels;
						}
						count++;
					}
					
				path.reset();
					
					path.moveTo(0, getHeight()-bottomPadding);
					path.lineTo(sidePadding, getHeight()-bottomPadding);
					path.lineTo(sidePadding, 0);
					path.lineTo(0, 0);
					path.close();
					canvas.drawPath(path, paint);
					
					path.reset();
					
					path.moveTo(getWidth(), getHeight()-bottomPadding);
					path.lineTo(getWidth()-sidePadding, getHeight()-bottomPadding);
					path.lineTo(getWidth()-sidePadding, 0);
					path.lineTo(getWidth(), 0);
					path.close();
					
					canvas.drawPath(path, paint);
					
				}
				
				lineCount++;
			}
			
			paint.reset();
			
			paint.setColor(this.gridColor);
			paint.setAlpha(255);
			paint.setAntiAlias(true);
			//canvas.drawLine(sidePadding, getHeight() - bottomPadding, getWidth(), getHeight()-bottomPadding, paint);
			if(this.showHorizontalGrid)
				for(int i=0;i<5;i++)
				{
					canvas.drawLine(sidePadding, getHeight() - bottomPadding-topPadding-(i*lineSpace), getWidth(), getHeight()-bottomPadding-topPadding-(i*lineSpace), paint);
				}
		
			paint.setAlpha(255);
			if(this.showVerticalGrid&&lines.size()>0)
			{
			
				Line line = lines.get(0);
					float maxY = getMaxY();
					float minY = getMinY();
					float maxX = getMaxX();
					float minX = getMinX();
					
					paint.setColor(this.gridColor);
					paint.setAlpha(255);
					int lastcheck=1;
						for (LinePoint p : line.getPoints()){
							float yPercent = (p.getY()-minY)/(maxY - minY);
							float xPercent = (p.getX()-minX)/(maxX - minX);
							float xPixels = sidePadding + (xPercent*usableWidth);
							float yPixels = getHeight() -(usableHeight*yPercent);
							canvas.drawLine((int)xPixels,getHeight()-bottomPadding-topPadding, (int)xPixels,0, paint);
							String xname = grouplist.get(lastcheck-1).getYear()+"-"+grouplist.get(lastcheck-1).getSemester();
							if(line.getPoints().size() == lastcheck)
								canvas.drawText(xname, (int)xPixels-50,getHeight()-topPadding+5, txtPaint);
							else
							canvas.drawText(xname, (int)xPixels-25,getHeight()-topPadding+5, txtPaint);
							lastcheck++;
				}
			}
			
			for (Line line : lines){
				int count = 0;
                float lastXPixels = 0, newYPixels;
                float lastYPixels = 0, newXPixels;
                float maxY = getMaxY();
				float minY = getMinY();
				float maxX = getMaxX();
				float minX = getMinX();
				
				paint.setColor(line.getColor());
				paint.setStrokeWidth(6);
				
				for (LinePoint p : line.getPoints()){
					float yPercent = (p.getY()-minY)/(maxY - minY);
					float xPercent = (p.getX()-minX)/(maxX - minX);
					if (count == 0){
						lastXPixels = sidePadding + (xPercent*usableWidth);
						lastYPixels = getHeight() - bottomPadding-topPadding- (usableHeight*yPercent);
					} else {
						newXPixels = sidePadding + (xPercent*usableWidth);
						newYPixels = getHeight() - bottomPadding -topPadding- (usableHeight*yPercent);
						canvas.drawLine(lastXPixels, lastYPixels, newXPixels, newYPixels, paint);
						lastXPixels = newXPixels;
						lastYPixels = newYPixels;
					}
					count++;
				}
			}
			
			
			int pointCount = 0;
			
			for (Line line : lines){
				float maxY = getMaxY();
				float minY = getMinY();
				float maxX = getMaxX();
				float minX = getMinX();
				
				paint.setColor(line.getColor());
				paint.setStrokeWidth(6);
				paint.setStrokeCap(Paint.Cap.ROUND);
				
				if (line.isShowingPoints()){
					for (LinePoint p : line.getPoints()){
						float yPercent = (p.getY()-minY)/(maxY - minY);
						float xPercent = (p.getX()-minX)/(maxX - minX);
						float xPixels = sidePadding + (xPercent*usableWidth);
						float yPixels = getHeight() - bottomPadding -topPadding- (usableHeight*yPercent);
						
						paint.setColor(Color.GRAY);
						canvas.drawCircle(xPixels, yPixels, 10, paint);
						paint.setColor(Color.WHITE);
						canvas.drawCircle(xPixels, yPixels, 5, paint);
						
						Path path2 = new Path();
						path2.addCircle(xPixels, yPixels, 30, Direction.CW);
						p.setPath(path2);
						p.setRegion(new Region((int)(xPixels-30), (int)(yPixels-30), (int)(xPixels+30), (int)(yPixels+30)));
						
						if (indexSelected == pointCount && listener != null){
							paint.setColor(Color.parseColor("#33B5E5"));
							paint.setAlpha(100);
							canvas.drawPath(p.getPath(), paint);
							paint.setAlpha(255);
						}
						
						pointCount++;
					}
				}
			}
			
			shouldUpdate = false;
            if (this.showMinAndMax) {
            	for(int i=0;i<5;i++)				
				ca.drawText(Integer.toString(i),0,getHeight() - bottomPadding-(i*lineSpace),txtPaint);
            	
				
            	ca.drawText(max, -1, bottomPadding-7, txtPaint);
			
            	}
		
		ca.drawBitmap(fullImage, 0, 0, null);
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

	    Point point = new Point();
	    point.x = (int) event.getX();
	    point.y = (int) event.getY();
	    
	    int count = 0;
	    int lineCount = 0;
        int pointCount;

        Region r = new Region();
	    for (Line line : lines){
	    	pointCount = 0;
	    	for (LinePoint p : line.getPoints()){
	    		
	    		if (p.getPath() != null && p.getRegion() != null){
	    			r.setPath(p.getPath(), p.getRegion());
                    if (r.contains(point.x, point.y) && event.getAction() == MotionEvent.ACTION_DOWN) {
                        indexSelected = count;
			    	} else if (event.getAction() == MotionEvent.ACTION_UP){
                        if (r.contains(point.x, point.y) && listener != null) {
                            listener.onClick(lineCount, pointCount);
			    		}
			    		indexSelected = -1;
			    	}
	    		}
		    	
		    	pointCount++;
			    count++;
	    	}
	    	lineCount++;
	    	
	    }
	    
	    if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP){
	    	shouldUpdate = true;
	    	postInvalidate();
	    }

	    return true;
	}
	
	public void setOnPointClickedListener(OnPointClickedListener listener) {
		this.listener = listener;
	}
	
	public interface OnPointClickedListener {
		abstract void onClick(int lineIndex, int pointIndex);
	}
}
