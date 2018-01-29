
&nbsp;&nbsp;&nbsp; If you find Pincode useful, please give it a 🌟 
# Pincode

## Screenshot

<center><img src="https://media.giphy.com/media/xT0xenUf82l9V0q68E/giphy.gif"></center>


## GetingStarted

In your `build.gradle`:


```java

		compile 'mm.mlika.android:pincode2:5.3'

```

In  `xml`:

```xml

		<mlikamohamed.com.pincode.PinCode
		xmlns:pin="http://schemas.android.com/apk/res-auto"
		android:layout_width="wrap_content"
		android:inputType="textVisiblePassword"
		android:layout_height="wrap_content"/>

```

the ```xml android:inputType="textVisiblePassword" ``` to remove suggestion 


#### if you want to only accept numbers 

In  `xml`:

```xml

		<mlikamohamed.com.pincode.PinCode
		xmlns:pin="http://schemas.android.com/apk/res-auto"
		android:layout_width="wrap_content"
		android:inputType="textVisiblePassword|number"
		android:digits="0123456789"
		android:layout_height="wrap_content"/>

```





#### set box count

by default it is 4 

In  `xml`:

```xml

		pin:boxCount="4"

```

In  `java`:

```java

		PinCode pinCode = findViewById(R.id.pinCode);
		pinCode.setBoxCount(5);

```

### box width and height

by default 38dp

In  `xml`:

```xml

		pin:boxWidthAndHeight="40dp"

```

In  `java`:

```java

		PinCode pinCode = findViewById(R.id.pinCode);
		pinCode.setBoxWidthAndHeight(40); // width and height is 40 pixel
		pinCode.setBoxWidthAndHeightDP(40); // width and height is 40 dp

```

#### change box color 

by default the color is grey 


In  `xml`:

```xml

		pin:boxColor="@color/colorAccent"


```

In  `java`:

```java

		pinCode.setBoxColor(ContextCompat.getColor(this,R.color.colorAccent));

```



#### change  Highlighted box color 

by default the color is black 


In  `xml`:

```xml

		pin:highlightedBoxColor="@color/colorPrimary"



```

In  `java`:

```java

		pinCode.setHighlightedBoxColor(ContextCompat.getColor(this,R.color.colorPrimary));


```


#### change  box stroke width 

by default the width is 1 dp

In  `xml`:


```xml

		pin:strokeWidth="2dp"



```

In  `java`:

```java

		pinCode.setStrokeWidth(2); // the width is 2 pixel
        pinCode.setStrokeWidthDP(2); // the width is 2 dp


```



#### change  margin between boxes  

by default the margin between boxes is 10dp

In  `xml`:


```xml

		pin:marginBetweenBox="5dp"




```

In  `java`:

```java

		pinCode.setMarginBetweenBox(10); // 10px
		pinCode.setMarginBetweenBoxDp(10); // 10dp


```


### show  text

In  `java`:


```java

		PinCode pinCode = findViewById(R.id.pinCode);
		pinCode.showText();
		pinCode.isTextVisible(); // return true

```


### hide  text

In  `java`:

```java

		PinCode pinCode = findViewById(R.id.pinCode);
		pinCode.hideText();
		pinCode.isTextVisible(); // return false

```



### change text size 

by default the size of the text is 18dp

In  `xml`:

```xml

		pin:textSize="30dp"


```



In  `java`:

```java

		pinCode.setTextSize(17); // text size 17 px 
		pinCode.setTextSizeDP(17); // text size 17 dp

```




### change text color 

by default the color is black

In  `xml`:

```xml

		pin:boxColor="@color/colorAccent"


```



In  `java`:

```java

		pinCode.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));


```



#### change picture 

In  `xml`:

```xml

		pin:imageResource="@drawable/icon_google"

```

In  `java`:

```java

		PinCode pinCode = findViewById(R.id.pinCode);
		pinCode.setImageResource(R.drawable.icon_google);
```



#### value change listener
In  `xml`:



In  `java`:

```java

		PinCode pinCode = findViewById(R.id.pinCode);
		pinCode.setTextChangeListener(new PinCode.OnTextChangeListener() {
		@Override
		public void onTextChanged(String text) {
                // this method will be called when text change
		}


		@Override
		public void onAllBoxFilled(String text) {
				// this method will be called when all boxes are filled
}
		});
```


## License

    Copyright 2017

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
