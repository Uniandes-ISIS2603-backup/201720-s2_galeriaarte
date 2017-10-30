(function(ng){
    var mod = ng.module("compraModule");
    mod.constant("comprasContext", "compras");
    mod.constant("clientesContext", "api/clientes");
    mod.controller('compraCtrl', ['$scope', '$http', 'clientesContext', '$state', 'comprasContext',
       function ($scope, $http, clientesContext, $state , comprasContext) {
           var idCompra =$state.params.compraId;
           $scope.deleteCompra = function(){
           $http.get(clientesContext + '/' + $state.params.clienteid + '/' + comprasContext).then(function (response) {
                $state.go('comprasList', {compraId: response.data.id}, {reload: true} );
            });
            
           };
       }
       
    ]);
}
)(angular);
