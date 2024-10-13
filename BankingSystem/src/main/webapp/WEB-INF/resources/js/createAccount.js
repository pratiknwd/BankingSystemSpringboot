$(document).ready(function() {
	$('#banks').change(function() {
		var bankName = $(this).val();
		var contextPath = $('#contextPath').val(); // Assuming context path is passed as a hidden field
		console.log("Selected bank: " + bankName); // Debugging log

		if (bankName) {
			$.ajax({
				url: contextPath + '/getIfscCodes',
				type: 'GET',
				data: { bankName: bankName },
				dataType: 'json',
				headers: {
					'Accept': 'application/json'
				},
				success: function(data) {
					console.log("Received IFSC codes: ", data); // Debugging log
					var ifscSelect = $('#IFSC');
					ifscSelect.empty();
					ifscSelect.append('<option value="">--Select IFSC code--</option>');
					$.each(data, function(index, value) {
						ifscSelect.append('<option value="' + value + '">' + value + '</option>');
					});
				},
				error: function(xhr, status, error) {
					console.error("Error fetching IFSC codes:", status, error);
					console.error("Response Text:", xhr.responseText);
					alert('Error fetching IFSC codes: ' + xhr.responseText);
					/*alert('Error fetching IFSC codes: ');*/
				}
			});
		} else {
			$('#IFSC').empty();
			$('#IFSC').append('<option value="">--Select IFSC code--</option>');
		}
	});
});
