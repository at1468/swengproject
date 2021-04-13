package datastorage;
import java.util.ArrayList;
import java.util.ListIterator;

/*
Lots of repeated/similar code, working on a better implementation
This should work in the mean time
*/


public class SlideDataStorage
{
    private String id;
    private int duration;
    private ArrayList<TextDataStorage> textArr = new ArrayList<>();
    private ArrayList<VideoDataStorage> videoArr = new ArrayList<>();
    private ArrayList<AudioDataStorage> audioArr = new ArrayList<>();
    private ArrayList<ImageDataStorage> imageArr = new ArrayList<>();
    private ArrayList<LineDataStorage> lineArr = new ArrayList<>();
    private ArrayList<ShapeDataStorage> shapeArr = new ArrayList<>();

    public SlideDataStorage(String id, int duration) {
        this.id = id;
        this.duration = duration;
    }

    //adders
    public void addText(TextDataStorage newText)
    {
        textArr.add(newText);
    }
    public void addVideo(VideoDataStorage newVideo)
    {
        videoArr.add(newVideo);
    }
    public void addAudio(AudioDataStorage newAudio)
    {
        audioArr.add(newAudio);
    }
    public void addImage(ImageDataStorage newImage)
    {
        imageArr.add(newImage);
    }
    public void addLine(LineDataStorage newLine)
    {
        lineArr.add(newLine);
    }
    public void addShape(ShapeDataStorage newShape)
    {
        shapeArr.add(newShape);
    }

    //hasElements
    public boolean hasText()
    {
        return !textArr.isEmpty();
    }
    public boolean hasVideo()
    {
        return !videoArr.isEmpty();
    }
    public boolean hasAudio()
    {
        return !audioArr.isEmpty();
    }
    public boolean hasImage()
    {
        return !imageArr.isEmpty();
    }
    public boolean hasLine()
    {
        return !lineArr.isEmpty();
    }
    public boolean hasShape()
    {
        return !shapeArr.isEmpty();
    }


    //iterators
    public ListIterator<TextDataStorage> textIterator()
    {
       return textArr.listIterator();
    }
    public ListIterator<VideoDataStorage> videoIterator()
    {
        return videoArr.listIterator();
    }
    public ListIterator<AudioDataStorage> audioIterator()
    {
        return audioArr.listIterator();
    }
    public ListIterator<ImageDataStorage> imageIterator()
    {
        return  imageArr.listIterator();
    }
    public ListIterator<LineDataStorage> lineIterator()
    {
        return lineArr.listIterator();
    }
    public ListIterator<ShapeDataStorage> shapeIterator()
    {
        return shapeArr.listIterator();
    }

}
