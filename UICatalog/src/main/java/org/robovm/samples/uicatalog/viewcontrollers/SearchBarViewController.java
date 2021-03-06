/*
 * Copyright (C) 2014 RoboVM AB
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
 * 
 * Portions of this code is based on Apple Inc's UICatalog sample (v2.11)
 * which is copyright (C) 2008-2013 Apple Inc.
 */

package org.robovm.samples.uicatalog.viewcontrollers;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIControl;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UIImage;
import org.robovm.apple.uikit.UISearchBar;
import org.robovm.apple.uikit.UISearchBarDelegateAdapter;
import org.robovm.apple.uikit.UISearchBarIcon;
import org.robovm.apple.uikit.UISegmentedControl;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewAutoresizing;
import org.robovm.apple.uikit.UIViewController;

/** The view controller for hosting the UIPickerView of this sample. */
public class SearchBarViewController extends UIViewController {

    private UISearchBar mySearchBar;
    private UISegmentedControl contentOptions;

    @Override
    public void viewDidLoad () {
        super.viewDidLoad();

        setView(new UIView(new CGRect(0, 20, 320, 460)));
        setTitle("SearchBarTitle");

        mySearchBar = new UISearchBar(new CGRect(0.0f, 65.0f, getView().getBounds().getWidth(), 44.0f));
        mySearchBar.setDelegate(new UISearchBarDelegateAdapter() {
            @Override
            public void searchButtonClicked (UISearchBar searchBar) {
                mySearchBar.resignFirstResponder();
            }

            @Override
            public void bookmarkButtonClicked (UISearchBar searchBar) {

            }

            @Override
            public void cancelButtonClicked (UISearchBar searchBar) {
                mySearchBar.resignFirstResponder();
            }

        });

        NSMutableArray<NSString> list = new NSMutableArray<NSString>();
        list.add(new NSString("Normal"));
        list.add(new NSString("Tinted"));
        list.add(new NSString("Background"));

        // mySearchBar.addListener
        contentOptions = new UISegmentedControl(NSArray.fromStrings("Normal", "Tinted", "Background"));
        contentOptions.setFrame(new CGRect(34, 125, 252, 30));

        mySearchBar.setShowsCancelButton(true);
        mySearchBar.setShowsBookmarkButton(true);

        contentOptions.addOnValueChangedListener(new UIControl.OnValueChangedListener() {
            /** contentChoice - changes background of search bar depending on option selection
             * 
             * @param selectedSegmentIndex */
            @Override
            public void onValueChanged (UIControl control) {
                mySearchBar.setTintColor(null);
                mySearchBar.setBackgroundImage(null);
                mySearchBar.setImageForSearchBarIcon(null, UISearchBarIcon.Bookmark, UIControlState.Normal);

                UISegmentedControl segControl = (UISegmentedControl)control;
                switch ((int)segControl.getSelectedSegment()) {
                case 1:
                    // tinted background
                    mySearchBar.setTintColor(UIColor.blue());
                    break;
                case 2:
                    // image background
                    mySearchBar.setBackgroundImage(UIImage.create("searchBarBackground"));
                    mySearchBar.setImageForSearchBarIcon(UIImage.create("bookmarkImage"), UISearchBarIcon.Bookmark,
                        UIControlState.Normal);
                    mySearchBar.setImageForSearchBarIcon(UIImage.create("bookmarkImageHighlighted"), UISearchBarIcon.Bookmark,
                        UIControlState.Highlighted);
                }
            }
        });

        getView().addSubview(mySearchBar);
        getView().addSubview(contentOptions);
        mySearchBar.setAutoresizingMask(UIViewAutoresizing.FlexibleWidth);

    }
}
