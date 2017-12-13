package thompson.flexion.entities;

import com.flexionmobile.codingchallenge.integration.Purchase;

public class GamePurchase implements Purchase {
	
	private boolean consumed;
	private String id;
	private String itemId;
	
	public boolean getConsumed() {
		return this.consumed;
	}

	public String getId() {
		return this.id;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (consumed ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GamePurchase other = (GamePurchase) obj;
		if (consumed != other.consumed) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (itemId == null) {
			if (other.itemId != null) {
				return false;
			}
		} else if (!itemId.equals(other.itemId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "GamePurchase [consumed=" + consumed + ", id=" + id + ", itemId=" + itemId + "]";
	}

}
