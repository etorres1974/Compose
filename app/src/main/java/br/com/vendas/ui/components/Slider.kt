package br.com.vendas.ui.components

import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun CustomSlider(
    name: String = "",
    initialValue: Float = 0f,
    showValue: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int = 0,
    onSliderChange: (Float) -> Unit = {}
) {
    var sliderPosition by remember { mutableStateOf(initialValue) }
    var text = name
    if (showValue)
        text += " : $sliderPosition"
    Text(text = text)

    Slider(value = sliderPosition,
        onValueChange = { sliderPosition = it },
        valueRange = valueRange,
        steps = steps,
        onValueChangeFinished = { onSliderChange(sliderPosition) }
    )
}
