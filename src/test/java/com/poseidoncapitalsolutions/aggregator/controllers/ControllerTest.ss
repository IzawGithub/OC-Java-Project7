╔═ createForm/com.poseidoncapitalsolutions.aggregator.controller.BidListController$$SpringCGLIB$$0 ═╗
<!DOCTYPE html>
<html
    class="h-dvh"
    data-theme="light" xmlns="http://www.w3.org/1999/xhtml"
>
    <head>
        <meta charset="UTF-8" />
        <link
            href="/favicon/favicon.svg"
            rel="icon"
        />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        
        <title>PoseidonCapitalSolution — Aggregator</title>
        <!-- TODO: Use an actual package manager instead of downloaded CDN file -->
        <link
            href="/css/bootstrap.min.css"
            rel="stylesheet"
            type="text/css"
        />
        <script src="/js/bootstrap.min.js"></script>
    </head>
    <body id="root" class="flex-1 flex-col">
        <div class="container">
            <div class="row">
                <h2>Add new Bid List</h2>
            </div>
            <form class="form-horizontal" method="post" action="/bidList" style="width: 100%"><input type="hidden" name="_csrf" value=""/>
        <div class="form-group">
            <label for="account" class="col-sm-2 control-label">Account</label>
            <div class="col-sm-10">
                <input
                    type="text"
                    id="account"
                    placeholder="Account"
                    class="col-4" name="account" value=""
                >
                
            </div>
        </div>
        <div class="form-group">
            <label for="type" class="col-sm-2 control-label">Type</label>
            <div class="col-sm-10">
                <input
                    type="text"
                    id="type"
                    placeholder="Type"
                    class="col-4" name="type" value=""
                >
                
            </div>
        </div>
        <div class="form-group">
            <label for="bidQuantity" class="col-sm-2 control-label"
            >Bid Quantity</label>
            <div class="col-sm-10">
                <input
                    type="number"
                    id="bidQuantity"
                    placeholder="Bid Quantity"
                    class="col-4" name="bidQuantity" value=""
                >
                
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-12">
                <a
                    class="btn btn-danger btn-sm"
                    href="/bidList"
                >Cancel</a>
                <input
                    class="btn btn-primary btn-sm"
                    type="submit"
                    value="Add Bid List"
                >
            </div>
        </div>
    </form>
        </div>
    </body>
</html>

╔═ createForm/com.poseidoncapitalsolutions.aggregator.controller.CurveController$$SpringCGLIB$$0 ═╗
<!DOCTYPE html>
<html
    class="h-dvh"
    data-theme="light" xmlns="http://www.w3.org/1999/xhtml"
>
    <head>
        <meta charset="UTF-8" />
        <link
            href="/favicon/favicon.svg"
            rel="icon"
        />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        
        <title>PoseidonCapitalSolution — Aggregator</title>
        <!-- TODO: Use an actual package manager instead of downloaded CDN file -->
        <link
            href="/css/bootstrap.min.css"
            rel="stylesheet"
            type="text/css"
        />
        <script src="/js/bootstrap.min.js"></script>
    </head>
    <body id="root" class="flex-1 flex-col">
        <div class="container">
            <div class="row">
                <h2>Add new Curve Point List</h2>
            </div>
            <form class="form-horizontal" method="post" action="/curvePoint" style="width: 100%"><input type="hidden" name="_csrf" value=""/>
        <div class="form-group">
            <label for="term" class="col-sm-2 control-label">Term</label>
            <div class="col-sm-10">
                <input
                    type="number"
                    id="term"
                    placeholder="Term"
                    class="col-4" name="term" value=""
                >
                
            </div>
        </div>
        <div class="form-group">
            <label for="value" class="col-sm-2 control-label">Value</label>
            <div class="col-sm-10">
                <input
                    type="number"
                    id="value"
                    placeholder="Value"
                    class="col-4" name="value" value=""
                >
                
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-12">
                <a
                    class="btn btn-danger btn-sm"
                    href="/curvePoint"
                >Cancel</a>
                <input
                    class="btn btn-primary btn-sm"
                    type="submit"
                    value="Add Curve Point"
                >
            </div>
        </div>
    </form>
        </div>
    </body>
</html>

╔═ createForm/com.poseidoncapitalsolutions.aggregator.controller.RatingController$$SpringCGLIB$$0 ═╗
<!DOCTYPE html>
<html
    class="h-dvh"
    data-theme="light" xmlns="http://www.w3.org/1999/xhtml"
>
    <head>
        <meta charset="UTF-8" />
        <link
            href="/favicon/favicon.svg"
            rel="icon"
        />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        
        <title>PoseidonCapitalSolution — Aggregator</title>
        <!-- TODO: Use an actual package manager instead of downloaded CDN file -->
        <link
            href="/css/bootstrap.min.css"
            rel="stylesheet"
            type="text/css"
        />
        <script src="/js/bootstrap.min.js"></script>
    </head>
    <body id="root" class="flex-1 flex-col">
        <div class="container">
            <div class="row">
                <h2>Add new Rating</h2>
            </div>
            <form class="form-horizontal" method="post" action="/rating" style="width: 100%"><input type="hidden" name="_csrf" value=""/>
        <div class="form-group">
            <label for="moodysRating" class="col-sm-2 control-label"
            >MoodysRating</label>
            <div class="col-sm-10">
                <input
                    type="number"
                    id="moodysRating"
                    placeholder="MoodysRating"
                    class="col-4" name="moodysRating" value=""
                >
                
            </div>
        </div>
        <div class="form-group">
            <label for="sandPRating" class="col-sm-2 control-label"
            >SandPRating</label>
            <div class="col-sm-10">
                <input
                    type="number"
                    id="sandPRating"
                    placeholder="SandPRating"
                    class="col-4" name="sandPRating" value=""
                >
                
            </div>
        </div>
        <div class="form-group">
            <label for="fitchRating" class="col-sm-2 control-label"
            >FitchRating</label>
            <div class="col-sm-10">
                <input
                    type="number"
                    id="fitchRating"
                    placeholder="FitchRating"
                    class="col-4" name="fitchRating" value=""
                >
                
            </div>
        </div>
        <div class="form-group">
            <label for="orderNumber" class="col-sm-2 control-label">Order number</label>
            <div class="col-sm-10">
                <input
                    type="text"
                    id="orderNumber"
                    placeholder="FitchRating"
                    class="col-4" name="orderNumber" value=""
                >
                
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-12">
                <a class="btn btn-danger btn-sm" href="/rating">Cancel</a>
                <input
                    class="btn btn-primary btn-sm"
                    type="submit"
                    value="Add Rating"
                >
            </div>
        </div>
    </form>
        </div>
    </body>
</html>

╔═ index/com.poseidoncapitalsolutions.aggregator.controller.BidListController$$SpringCGLIB$$0 ═╗
<!DOCTYPE html>
<html
    class="h-dvh"
    data-theme="light" xmlns="http://www.w3.org/1999/xhtml"
>
    <head>
        <meta charset="UTF-8" />
        <link
            href="/favicon/favicon.svg"
            rel="icon"
        />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        
        <title>PoseidonCapitalSolution — Aggregator</title>
        <!-- TODO: Use an actual package manager instead of downloaded CDN file -->
        <link
            href="/css/bootstrap.min.css"
            rel="stylesheet"
            type="text/css"
        />
        <script src="/js/bootstrap.min.js"></script>
    </head>
    <body id="root" class="flex-1 flex-col">
        <div class="container">
            <div class="row">
                <div class="col-6">
                    <a href="/bidList">Bid List</a> 
                    <a href="/curvePoint">Curve Points</a> 
                    <a href="/rating">Ratings</a> 
                    <a href="/ruleName">Rule</a> 
                    <a href="/trade">Trade</a> 
                </div>
                <div class="col-6 text-right">
                    Logged in user: <b
                        class="user"
                    >John Doe</b>
                    <form action="/auth/log-out" method="POST"><input type="hidden" name="_csrf" value=""/>
                        <input type="submit" value="Logout" />
                    </form>
                </div>
            </div>
            <div class="row">
                <h2>Bid List</h2>
            </div>
            <div class="row">
        <a href="/bidList/create" class="btn btn-primary btn-sm">Add New</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Account</th>
                    <th>Type</th>
                    <th>Bid Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </table>
    </div>
        </div>
    </body>
</html>

</html>

╔═ index/com.poseidoncapitalsolutions.aggregator.controller.CurveController$$SpringCGLIB$$0 ═╗
<!DOCTYPE html>
<html
    class="h-dvh"
    data-theme="light" xmlns="http://www.w3.org/1999/xhtml"
>
    <head>
        <meta charset="UTF-8" />
        <link
            href="/favicon/favicon.svg"
            rel="icon"
        />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        
        <title>PoseidonCapitalSolution — Aggregator</title>
        <!-- TODO: Use an actual package manager instead of downloaded CDN file -->
        <link
            href="/css/bootstrap.min.css"
            rel="stylesheet"
            type="text/css"
        />
        <script src="/js/bootstrap.min.js"></script>
    </head>
    <body id="root" class="flex-1 flex-col">
        <div class="container">
            <div class="row">
                <div class="col-6">
                    <a href="/bidList">Bid List</a> 
                    <a href="/curvePoint">Curve Points</a> 
                    <a href="/rating">Ratings</a> 
                    <a href="/ruleName">Rule</a> 
                    <a href="/trade">Trade</a> 
                </div>
                <div class="col-6 text-right">
                    Logged in user: <b
                        class="user"
                    >John Doe</b>
                    <form action="/auth/log-out" method="POST"><input type="hidden" name="_csrf" value=""/>
                        <input type="submit" value="Logout" />
                    </form>
                </div>
            </div>
            <div class="row">
                <h2>Curve Point List</h2>
            </div>
            <div class="row">
        <a href="/curvePoint/create" class="btn btn-primary btn-sm">Add New</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>CurvePointId</th>
                    <th>Term</th>
                    <th>Value</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </table>
    </div>
        </div>
    </body>
</html>

</html>

╔═ index/com.poseidoncapitalsolutions.aggregator.controller.RatingController$$SpringCGLIB$$0 ═╗
<!DOCTYPE html>
<html
    class="h-dvh"
    data-theme="light" xmlns="http://www.w3.org/1999/xhtml"
>
    <head>
        <meta charset="UTF-8" />
        <link
            href="/favicon/favicon.svg"
            rel="icon"
        />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        
        <title>PoseidonCapitalSolution — Aggregator</title>
        <!-- TODO: Use an actual package manager instead of downloaded CDN file -->
        <link
            href="/css/bootstrap.min.css"
            rel="stylesheet"
            type="text/css"
        />
        <script src="/js/bootstrap.min.js"></script>
    </head>
    <body id="root" class="flex-1 flex-col">
        <div class="container">
            <div class="row">
                <div class="col-6">
                    <a href="/bidList">Bid List</a> 
                    <a href="/curvePoint">Curve Points</a> 
                    <a href="/rating">Ratings</a> 
                    <a href="/ruleName">Rule</a> 
                    <a href="/trade">Trade</a> 
                </div>
                <div class="col-6 text-right">
                    Logged in user: <b
                        class="user"
                    >John Doe</b>
                    <form action="/auth/log-out" method="POST"><input type="hidden" name="_csrf" value=""/>
                        <input type="submit" value="Logout" />
                    </form>
                </div>
            </div>
            <div class="row">
                <h2>Rating List</h2>
            </div>
            <div class="row">
        <a href="/rating/create" class="btn btn-primary btn-sm">Add New</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>MoodysRating</th>
                    <th>SandPRating</th>
                    <th>FitchRating</th>
                    <th>Order</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </table>
    </div>
        </div>
    </body>
</html>

</html>

╔═ updateForm/com.poseidoncapitalsolutions.aggregator.controller.BidListController$$SpringCGLIB$$0 ═╗
<!DOCTYPE html>
<html
    class="h-dvh"
    data-theme="light" xmlns="http://www.w3.org/1999/xhtml"
>
    <head>
        <meta charset="UTF-8" />
        <link
            href="/favicon/favicon.svg"
            rel="icon"
        />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        
        <title>PoseidonCapitalSolution — Aggregator</title>
        <!-- TODO: Use an actual package manager instead of downloaded CDN file -->
        <link
            href="/css/bootstrap.min.css"
            rel="stylesheet"
            type="text/css"
        />
        <script src="/js/bootstrap.min.js"></script>
    </head>
    <body id="root" class="flex-1 flex-col">
        <div class="container">
            <div class="row">
                <h2>Update Bid List</h2>
            </div>
            <form class="form-horizontal" method="post" action="/bidList" style="width: 100%"><input type="hidden" name="_csrf" value=""/>
        <input type="hidden" name="id" value="76543210-dcba-cba1-dcba-ba9876543210"/>
        <div class="form-group">
            <label for="account" class="col-sm-2 control-label">Account</label>
            <div class="col-sm-10">
                <input
                    type="text"
                    id="account"
                    placeholder="Account"
                    class="col-4" name="account" value="Account Test"
                >
                
            </div>
        </div>
        <div class="form-group">
            <label for="type" class="col-sm-2 control-label">Type</label>
            <div class="col-sm-10">
                <input
                    type="text"
                    id="type"
                    placeholder="Type"
                    class="col-4" name="type" value="Type test"
                >
                
            </div>
        </div>
        <div class="form-group">
            <label for="bidQuantity" class="col-sm-2 control-label"
            >Bid Quantity</label>
            <div class="col-sm-10">
                <input
                    type="number"
                    id="bidQuantity"
                    placeholder="Bid Quantity"
                    class="col-4" name="bidQuantity" value="10.0"
                >
                
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-12">
                <a
                    class="btn btn-danger btn-sm"
                    href="/bidList"
                >Cancel</a>
                <input
                    class="btn btn-primary btn-sm"
                    type="submit"
                    value="Update Bid List"
                >
            </div>
        </div>
    </form>
        </div>
    </body>
</html>

╔═ updateForm/com.poseidoncapitalsolutions.aggregator.controller.CurveController$$SpringCGLIB$$0 ═╗
<!DOCTYPE html>
<html
    class="h-dvh"
    data-theme="light" xmlns="http://www.w3.org/1999/xhtml"
>
    <head>
        <meta charset="UTF-8" />
        <link
            href="/favicon/favicon.svg"
            rel="icon"
        />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        
        <title>PoseidonCapitalSolution — Aggregator</title>
        <!-- TODO: Use an actual package manager instead of downloaded CDN file -->
        <link
            href="/css/bootstrap.min.css"
            rel="stylesheet"
            type="text/css"
        />
        <script src="/js/bootstrap.min.js"></script>
    </head>
    <body id="root" class="flex-1 flex-col">
        <div class="container">
            <div class="row">
                <h2>Update Curve Point</h2>
            </div>
            <form class="form-horizontal" method="post" action="/curvePoint" style="width: 100%"><input type="hidden" name="_csrf" value=""/>
        <input type="hidden" name="id" value="76543210-dcba-cba1-dcba-ba9876543210" />
        <div class="form-group">
            <label for="term" class="col-sm-2 control-label">Term</label>
            <div class="col-sm-10">
                <input
                    type="number"
                    id="term"
                    placeholder="Term"
                    class="col-4" name="term" value="10.0"
                >
                
            </div>
        </div>
        <div class="form-group">
            <label for="value" class="col-sm-2 control-label">Value</label>
            <div class="col-sm-10">
                <input
                    type="number"
                    id="value"
                    placeholder="Value"
                    class="col-4" name="value" value="30.0"
                >
                
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-12">
                <input type="hidden" id="id" name="id" value="76543210-dcba-cba1-dcba-ba9876543210">
                <a
                    class="btn btn-danger btn-sm"
                    href="/curvePoint"
                >Cancel</a>
                <input
                    class="btn btn-primary btn-sm"
                    type="submit"
                    value="Update Curve Point"
                >
            </div>
        </div>
    </form>
        </div>
    </body>
</html>

╔═ updateForm/com.poseidoncapitalsolutions.aggregator.controller.RatingController$$SpringCGLIB$$0 ═╗
<!DOCTYPE html>
<html
    class="h-dvh"
    data-theme="light" xmlns="http://www.w3.org/1999/xhtml"
>
    <head>
        <meta charset="UTF-8" />
        <link
            href="/favicon/favicon.svg"
            rel="icon"
        />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        
        <title>PoseidonCapitalSolution — Aggregator</title>
        <!-- TODO: Use an actual package manager instead of downloaded CDN file -->
        <link
            href="/css/bootstrap.min.css"
            rel="stylesheet"
            type="text/css"
        />
        <script src="/js/bootstrap.min.js"></script>
    </head>
    <body id="root" class="flex-1 flex-col">
        <div class="container">
            <div class="row">
                <h2>Update Rating</h2>
            </div>
            <form class="form-horizontal" method="post" action="/rating" style="width: 100%"><input type="hidden" name="_csrf" value=""/>
        <input type="hidden" name="id" value="76543210-dcba-cba1-dcba-ba9876543210" />
        <div class="form-group">
            <label for="moodysRating" class="col-sm-2 control-label"
            >MoodysRating</label>
            <div class="col-sm-10">
                <input
                    type="number"
                    id="moodysRating"
                    placeholder="MoodysRating"
                    class="col-4" name="moodysRating" value="Moodys Rating"
                >
                
            </div>
        </div>
        <div class="form-group">
            <label for="sandPRating" class="col-sm-2 control-label"
            >SandPRating</label>
            <div class="col-sm-10">
                <input
                    type="number"
                    id="sandPRating"
                    placeholder="SandPRating"
                    class="col-4" name="sandPRating" value="Sand PRating"
                >
                
            </div>
        </div>
        <div class="form-group">
            <label for="fitchRating" class="col-sm-2 control-label"
            >FitchRating</label>
            <div class="col-sm-10">
                <input
                    type="number"
                    id="fitchRating"
                    placeholder="FitchRating"
                    class="col-4" name="fitchRating" value="Fich Rating"
                >
                
            </div>
        </div>
        <div class="form-group">
            <label for="order" class="col-sm-2 control-label">Order</label>
            <div class="col-sm-10">
                <input
                    type="text"
                    id="orderNumber"
                    placeholder="FitchRating"
                    class="col-4" name="orderNumber" value="10"
                >
                
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-12">
                <input type="hidden" id="id" name="id" value="76543210-dcba-cba1-dcba-ba9876543210">
                <a class="btn btn-danger btn-sm" href="/rating">Cancel</a>
                <input
                    class="btn btn-primary btn-sm"
                    type="submit"
                    value="Update Rating"
                >
            </div>
        </div>
    </form>
        </div>
    </body>
</html>

╔═ [end of file] ═╗
