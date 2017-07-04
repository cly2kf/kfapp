package talex.zsw.baselibrary.view.TransferImage;

import android.graphics.Color;
import android.widget.ImageView;

import java.util.List;

import talex.zsw.baselibrary.view.TransferImage.loader.ImageLoader;
import talex.zsw.baselibrary.view.TransferImage.style.IIndexIndicator;
import talex.zsw.baselibrary.view.TransferImage.style.IProgressIndicator;
import talex.zsw.baselibrary.view.TransferImage.style.ITransferAnimator;

/**
 * Attributes <br/>
 *
 * Created by hitomi on 2017/1/19.
 */
class TransferAttr {

    private List<ImageView> originImageList;
    private int currOriginIndex, currShowIndex;
    private int backgroundColor;
    private int offscreenPageLimit;
    private int missPlaceHolder;

    private ITransferAnimator transferAnima;
    private IProgressIndicator progressIndicator;
    private IIndexIndicator indexIndicator;
    private ImageLoader imageLoader;

    private List<String> imageStrList;

    List<ImageView> getOriginImageList() {
        return originImageList;
    }

    void setOriginImageList(List<ImageView> originImageList) {
        this.originImageList = originImageList;
    }

    int getBackgroundColor() {
        return backgroundColor;
    }

    void setBackgroundColor(int backgroundColor) {
        if (backgroundColor == 0) {
            this.backgroundColor = Color.BLACK;
        } else {
            this.backgroundColor = backgroundColor;
        }
    }

    List<String> getImageStrList() {
        return imageStrList;
    }

    void setImageUrlList(List<String> imageStrList) {
        this.imageStrList = imageStrList;
    }

    ITransferAnimator getTransferAnima() {
        return transferAnima;
    }

    void setTransferAnima(ITransferAnimator transferAnima) {
        this.transferAnima = transferAnima;
    }

    IProgressIndicator getProgressIndicator() {
        return progressIndicator;
    }

    void setProgressIndicator(IProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
    }

    IIndexIndicator getIndexIndicator() {
        return indexIndicator;
    }

    void setIndexIndicator(IIndexIndicator indexIndicator) {
        this.indexIndicator = indexIndicator;
    }

    ImageLoader getImageLoader() {
        return imageLoader;
    }

    void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    int getCurrOriginIndex() {
        return currOriginIndex;
    }

    void setCurrOriginIndex(int currOriginIndex) {
        if (currOriginIndex >= originImageList.size()) return;
        this.currOriginIndex = currOriginIndex;
    }

    int getCurrShowIndex() {
        return currShowIndex;
    }

    void setCurrShowIndex(int currShowIndex) {
        this.currShowIndex = currShowIndex;
    }

    ImageView getCurrOriginImageView() {
        return originImageList.get(currOriginIndex);
    }

    int getOffscreenPageLimit() {
        return offscreenPageLimit;
    }

    void setOffscreenPageLimit(int offscreenPageLimit) {
        this.offscreenPageLimit = offscreenPageLimit;
    }

    int getMissPlaceHolder() {
        return missPlaceHolder;
    }

    void setMissPlaceHolder(int missPlaceHolder) {
        this.missPlaceHolder = missPlaceHolder;
    }
}
