/*
 * Copyright (C) 2016 Peng fei Pan <sky@xiaopan.me>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.xiaopan.sketch.state;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.xiaopan.sketch.SketchView;
import me.xiaopan.sketch.drawable.SketchShapeBitmapDrawable;
import me.xiaopan.sketch.request.DisplayOptions;
import me.xiaopan.sketch.request.ShapeSize;
import me.xiaopan.sketch.shaper.ImageShaper;

/**
 * 给什么图片显示什么图片，支持ShapeSize和ImageShaper
 */
@SuppressWarnings("unused")
public class DrawableStateImage implements StateImage {
    private Drawable originDrawable;
    private int resId = -1;

    public DrawableStateImage(Drawable drawable) {
        this.originDrawable = drawable;
    }

    public DrawableStateImage(int resId) {
        this.resId = resId;
    }

    @Nullable
    @Override
    public Drawable getDrawable(@NonNull Context context, @NonNull SketchView sketchView, @NonNull DisplayOptions displayOptions) {
        Drawable drawable = originDrawable;
        if (drawable == null && resId != -1) {
            drawable = context.getResources().getDrawable(resId);
        }

        ShapeSize shapeSize = displayOptions.getShapeSize();
        ImageShaper imageShaper = displayOptions.getShaper();
        if ((shapeSize != null || imageShaper != null) && drawable != null
                && drawable instanceof BitmapDrawable) {
            drawable = new SketchShapeBitmapDrawable(context, (BitmapDrawable) drawable, shapeSize, imageShaper);
        }

        return drawable;
    }

    public Drawable getOriginDrawable() {
        return originDrawable;
    }

    public int getResId() {
        return resId;
    }
}
