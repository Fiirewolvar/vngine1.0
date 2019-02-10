package outdated;
public class Character {

	private String id;
	private String name;
	private String sprite;
	// contains image location of current sprite.

	public Character(String id, String name, String sprite) {
		this.id = id;
		this.name = name;
		this.sprite = sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public String getSprite() {
		return this.sprite;
	}

	public String getName() {
		return this.name;
	}

	public String getId() {
		return this.id;
	}
}