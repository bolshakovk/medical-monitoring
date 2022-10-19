package antisolid;

import java.util.ArrayList;
import java.util.List;
//нужно было использовать интерфейс
public class AntiD {
    List<RockMusic> rockMusicList = new ArrayList<>();
    List<JazzMusic> jazzMusicsList = new ArrayList<>();
    List<BuggiMusic> buggiMusics = new ArrayList<>();
    public void addMusic(String name){
        RockMusic rockMusic = new RockMusic(name);
        rockMusicList.add(rockMusic);
    }
    public void playMusic(List<RockMusic> list){
        for (RockMusic item : list){
            item.playMusic();
        }
    }
}
class RockMusic{
    private String name;

    public RockMusic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void playMusic(){
        System.out.println("playing rock " + name);
    }
}
class JazzMusic{
    public void playMusic(){
        System.out.println("playing jazz");
    }
}
class BuggiMusic{
    public void playMusic(){
        System.out.println("playing buggi");
    }
}
