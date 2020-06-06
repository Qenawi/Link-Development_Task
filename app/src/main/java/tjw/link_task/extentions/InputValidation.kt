package tjw.link_task.extentions

enum class InputType {
    Age,
    Name,
    Address,
    Email,
    Password, LocationString,
    LocationLatLng
}
fun <T : Any?> T?.validateInput(inputType: InputType): Boolean = this?.let { input ->
    return@let when (inputType) {
        InputType.Name -> (input as String).length >= 3
        InputType.Address -> (input as String).length >= 5
        InputType.Email -> false
        InputType.Password -> false
        InputType.LocationString -> (input as String).length >= 10
        InputType.LocationLatLng -> true
        InputType.Age -> (input as String).length >= 2
    }
} ?: false

