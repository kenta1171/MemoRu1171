package Source;

/**
 * ページング機能周り
 */
public class MemoRuPager {
	/**
	 * 1ページ中に表示される最大件数
	 */
	public static final int OUTPUT_NUM = 10;

	/**
	 * 全レコードの件数
	 */
	private long recordNum;

	/**
	 * 現在のページ
	 */
	private int currentPage = 1;

	/**
	 * 全レコードの件数から算出されるページ数（recordNum/outputNum）
	 */
	private long maxPage;
	
	/**
	 * ページングに使う変数を設定する
	 * @param recordNum
	 */
	public void config(Long recordNum) {
		this.recordNum = recordNum;
		this.maxPage = this.recordNum / OUTPUT_NUM;

		if (this.recordNum % OUTPUT_NUM != 0
				|| this.recordNum == 0) {
			++this.maxPage;
		}
	}
	
	/**
	 * 前ページボタンを表示するか
	 * @return
	 */
	public boolean prevPageDisplay() {
		return currentPage > 1;
	}

	/**
	 * 次ページボタンを表示するか
	 * @return
	 */
	public boolean nextPageDisplay() {
		return currentPage < maxPage;
	}
	
	/**
	 * 最初のページへ
	 */
	public void topPage() {
		currentPage = 1;
	}

	/**
	 * 最後のページへ
	 */
	public void lastPage() {
		currentPage = (int) maxPage;
	}

	/**
	 * 前のページへ
	 */
	public void prevPage() {
		if (prevPageDisplay()) {
			currentPage--;
		}
	}

	/**
	 * 次のページへ
	 */
	public void nextPage() {
		if (nextPageDisplay()) {
			currentPage++;
		}
	}
	
	/**
	 * 何番目のレコードから表示するか
	 * @return
	 */
	public Integer offset() {
		return (currentPage - 1) * OUTPUT_NUM;
	}
}
