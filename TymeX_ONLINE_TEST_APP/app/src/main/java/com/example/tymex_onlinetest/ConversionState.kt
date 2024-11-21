sealed class ConversionState {
    object Initial : ConversionState()
    object Loading : ConversionState()
    data class Success(
        val amount: Double,
        val fromCurrency: String,
        val convertedAmount: Double,
        val toCurrency: String
    ) : ConversionState()
    data class Error(val message: String) : ConversionState() // Lỗi với thông báo
}
