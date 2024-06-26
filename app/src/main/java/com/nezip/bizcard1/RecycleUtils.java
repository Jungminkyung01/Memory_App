/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nezip.bizcard1;

import java.lang.ref.WeakReference;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

/**
 * @author givenjazz 
 *
 */
public class RecycleUtils {
	
	private RecycleUtils(){};

    public static void recursiveRecycle(View root) {
        if (root == null)
            return;
        
        root.setBackgroundDrawable(null);

        if (root instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)root;
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                recursiveRecycle(group.getChildAt(i));
            }

            if (!(root instanceof AdapterView)) {
                group.removeAllViews();
            }

        }
        
        if (root instanceof ImageView) {
            ((ImageView)root).setImageBitmap(null);
        }

        root = null;

        return;
    }
    
    public static void recursiveRecycle(List<WeakReference<View>> recycleList) {
		for (WeakReference<View> ref : recycleList) {
			recursiveRecycle(ref.get());
		}
	}
}
