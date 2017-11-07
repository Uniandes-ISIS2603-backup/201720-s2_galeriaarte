(
        function (ng) {
            var mod = ng.module("comentarioModule");
            mod.constant("comentariosContext", "api/comentarios");
            mod.constant("clientesContext", "api/cliente");
            mod.controller('comentarioUpdateCtrl', ['$scope', '$http', 'comentariosContext', '$state', 'clientesContext', '$rootScope', '$filter',
                function ($scope, $http, comentariosContext, $state, clientesContext, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idComentario = $state.params.comentarioId;

                    // Este arreglo guardara los ids de los books asociados y por asociar al autor.
                    var idCliente = [];

                    // Este arreglo mostrará los books una vez esten filtrados visualmente por lo que el autor ya tiene asociado.
                    $scope.allClientesShow = [];

                    //Consulto el autor a editar.
                    $http.get(comentariosContext + '/' + idComentario).then(function (response) {
                        var comentario = response.data;
                        $scope.comentarioId = comentario.id;
                        $scope.comentarioContenido = comentario.contenido;
                        $scope.allClienteComentarios = comentario.cliente;
                        $scope.mergeClientes($scope.allClienteComentarios);
                    });

                    /*
                     * Esta función añade los ids de los books que ya tiene el autor asociado.
                     * @param {type} books: Son los books que ya tiene asociado el autor.
                     * @returns {undefined}
                     */
                    $scope.mergeClientes = function (clientes) {
                        for (var item in clientes) {
                            idCliente.push("" + clientes[item].id);
                        }
                        $scope.getClientes(clientes);
                    };

                    /*
                     * Esta función recibe como param los books que tiene el autor para hacer un filtro visual con todos los books que existen.
                     * @param {type} books
                     * @returns {undefined}
                     */
                    $scope.getClientes = function (clientes) {
                        $http.get(clientesContext).then(function (response) {
                            $scope.Allclientes = response.data;
                            $scope.clientesComentario = clientes;

                            var filteredClientes = $scope.Allclientes.filter(function (Allclientes) {
                                return $scope.clientesComentario.filter(function (clientesComentario) {
                                    return clientesComentario.id == Allclientes.id;
                                }).length == 0
                            });

                            $scope.allClientesShow = filteredClientes;

                        });
                    };


                    //funciones para el drag and drop de HTML5 nativo
                    $scope.allowDrop = function (ev) {
                        ev.preventDefault();
                    };

                    $scope.drag = function (ev) {
                        ev.dataTransfer.setData("text", ev.target.id);
                    };

                    $scope.dropAdd = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        //Cuando un book se añade al autor, se almacena su id en el array idsBook
                        idCliente.push("" + data);
                    };

                    $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        //Para remover el book que no se va asociar, por eso se usa el splice que quita el id del book en el array idsBook
                        var index = idCliente.indexOf(data);
                        if (index > -1) {
                            idCliente.splice(index, 1);
                        }
                    };

                    $scope.createComentario = function () {
                        /*Se llama a la función newBooks() para buscar cada uno de los ids de los books
                         en el array que tiene todos los books y así saber como queda la lista final de los books asociados al autor.
                         */
                        $scope.newClientes();
                        $http.put(comentariosContext + "/" + idComentario, {
//                            id: 0,
                            id: $scope.comentarioId,
                            contenido: $scope.comentarioContenido
                        }).then(function (response) {
//                            if (idsComentario.length > 0) {
//                                $http.put(clientesContext + "/" + response.data.id + "/comentarios", $scope.allComentariosCliente).then(function (response) {
//                                });
//                            }
                            //Author created successfully
                            $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                        });
                    };

                    $scope.newClientes = function () {
                        $scope.allClientesComentario = [];
                        for (var ite in idCliente) {
                            for (var all in $scope.Allclientes) {
                                if ($scope.Allclientes[all].id === parseInt(idCliente[ite])) {
                                    $scope.allClientesComentaio.push($scope.Allclientes[all]);
                                }
                            }
                        }
                    };
                }
            ]);
        }
)(window.angular);