package waveformmaker;

import graphicshandler.GraphicsPanel;
import wavreading.WavFile;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;

public class ReadWAV
{
	public static void main(String[] args)
	{
		try
		{
			//Open the file
			File file =  new File("src/main/resources/RedKite1.wav");

			String name = file.getName().replace("."," ");
			System.out.println(name);

			// Open the wav file specified as the first argument
			WavFile wavFile = WavFile.openWavFile(file);

			// Get the number of audio channels in the wav file
			int numChannels = wavFile.getNumChannels();

			// Get the number of audio samples (frames) in the wav file - 1 frame can have multiple channels
			double numFrames = wavFile.getNumFrames();

			//Width of the waveform graphic (duration) in pixels, essentially the number of lines used to represent the waveform
			int waveformWidth = 360;

			//Height of the waveform graphic (max amplitude) in pixels
			int waveformHeight = 100;

			//Starting point of the waveform graphic in pixels
			int waveformStart = 15;

			//Location of the centre line / zero value of the waveform graphic in pixels
			int centreLine = 100;

			System.out.println("Frames: " + numFrames);

			int bufferSize = (int) Math.ceil(numFrames/waveformWidth);
			System.out.println("Buffer size: " + bufferSize);

			// Create a buffer with number of frames determined by bufferSize
			double[] buffer = new double[bufferSize * numChannels];

			//Store the max sample from each buffer
			double[] maxSample = new double[waveformWidth];

			int framesRead;
			double max = Double.MIN_VALUE;

			int i = 0;
			do {
				// Read frames into buffer
				framesRead = wavFile.readFrames(buffer, bufferSize);
				System.out.println("Frames read: " + framesRead + " in run: " + i);

				// Loop through frames and look for minimum and maximum value
				for (int j = 0 ; j < framesRead * numChannels ; j++) {
					if (buffer[j] > max) {
						max = buffer[j];
					}
				}

				maxSample[i] = max;

				max = Double.MIN_VALUE;
				i++;
			}
			while (framesRead == bufferSize);

			//Set up a frame and panel to check waveform has been generated properly
			JFrame f = new JFrame();                            //Create new JFrame, acting as the main window
			GraphicsPanel gp = new GraphicsPanel();
			f.setVisible(true);                                 //Set the frame to visible
			f.setSize(1600, 900);                   //Set size to 1600x900 pix
			f.setTitle("Graphics Demo");                        //Set frame title to "Graphics Test"
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Will exit out when close button pressed
			f.add(gp);                                          //Add the GraphicsPanel object to the frame

//			PrintWriter xmlWriter = new PrintWriter(,"UTF-8")

			for (int s = 0; s < waveformWidth; s++) {
				gp.addLine(waveformStart + s, centreLine + (int)(waveformHeight * maxSample[s])/2, waveformStart + s, centreLine - (int)(waveformHeight * maxSample[s])/2, "#b0b3b8", 0);
			}
			gp.repaint();

			System.out.println("<line xstart=\"" + waveformStart + "\"" + " ystart=\"" + (centreLine + (int)(waveformHeight * maxSample[0])/2) + "\""
					+ " xend=\"" + waveformStart + "\"" + " yend=\"" + (centreLine + (int)(waveformHeight * maxSample[0])/2) + "\"" + " linecolour=\""
					+ "#b0b3b8" + "\"" + " duration=\"0\"/>");

			// Close the wavFile
			wavFile.close();
		}

		catch (Exception e) {
			System.err.println(e);
		}
	}
}
