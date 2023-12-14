var app = angular.module("myapp", []).controller(
		"myController",
		function($scope, $http) {

			var BASE_PATH = "http://localhost:8080";

			$scope.getProductList = function() {
				$http.get(BASE_PATH + "/getProductsList")
						.success(function(data) {
							$scope.products = data;
						});
			}

			$scope.addToCart = function(productId) {
				
				console.log(productId);
				$http.get(BASE_PATH + "/cart/add/" + productId)
						.success(function() {
							alert("Added Successfully");
						})
			}

			$scope.refreshCart = function() {
				$http.get(BASE_PATH + "/cart/getCart/" + $scope.cartId)
				     .success(function(data) {
						 alert("got the data from server!!!!!");
                    console.log(data);
					$scope.carts = data;
					
				})
			}

			$scope.getCart = function(cartId) {
				alert("calling get cart!!!!!");
				console.log(cartId);
				$scope.cartId = cartId;
				$scope.refreshCart(cartId);
			}
			$scope.removeFromCart = function(cartItemId) {
				$http.put(BASE_PATH +"/cart/removeCartItem/"
								+ cartItemId).success(function() {
					$scope.refreshCart();
				});
			}
			
			
			$scope.clearCart = function() {
				$http.put(BASE_PATH + "/cart/removeAllItems/"
								+ $scope.cartId).success(function() {
					$scope.refreshCart();
				});
			}

			$scope.calculateGrandTotal = function() {
				var grandTotal = 0.0;
				for (var i = 0; i < $scope.carts.cartItem.length; i++)
					grandTotal = grandTotal + $scope.carts.cartItem[i].price;
				return grandTotal;

			}
		});
