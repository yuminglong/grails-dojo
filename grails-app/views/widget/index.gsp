<html>
<head>
  <title>Widget Tester Page</title>
  <g:javascript library="dojo"/>
  <g:javascript>
    function beforeEvent() {
      console.log("Before");
    }

    function after() {
      console.log("After");
    }

    function onSuccess() {
      console.log("On Success");
    }

    function onFailure() {
      console.log("On Failure");
    }

    function onLoading() {
      console.log("Loading");
    }

    function onLoaded() {
      console.log("Loaded");
    }

    function onComplete() {
      console.log("Complete");
    }
    function onFailure404() {
      console.log("Had a 404 failure.")
    }
    function onFailure200() {
      console.log("Had a 200 failure.")
    }


  </g:javascript>
</head>
<body>

<ul>
  <li>
    <g:remoteLink name="robsLink" action="remotePage" update="[success:'myUpdateRegion',failure:'myUpdateRegion2']"
            before="beforeEvent()"
            after="after()"
            onSuccess="onSuccess()"
            onFailure="onFailure()"
            onLoading="onLoading()"
            onLoaded="onLoaded()"
            onComplete="onComplete()"
            on404="onFailure404()"
            on200="onFailure200()">
      Click Here to Test
    </g:remoteLink>
  </li>

  <li>
    <g:remoteLink action="remotePage2" update="[success:'myUpdateRegion',failure:'myUpdateRegion2']"
            before="beforeEvent()"
            after="after()"
            onSuccess="onSuccess()"
            onFailure="onFailure()"
            onLoading="onLoading()"
            onLoaded="onLoaded()"
            onComplete="onComplete()"
            on404="onFailure404()">
      Click to Fail
    </g:remoteLink>
  </li>

  <li>
    <g:remoteLink action="remotePage2" update="myUpdateRegion">
      Fail without Handlers
    </g:remoteLink>
  </li>
  <li>
    <g:remoteLink action="remotePage" update="myUpdateRegion" method="post">
      Do Post Request
    </g:remoteLink>
  </li>
  <li>
    <g:remoteLink  action="remotePage" update="myUpdateRegion" asynchronous="false">
      Synchronized
    </g:remoteLink>
  </li>
</ul>

<div style="width:400px; height:300px; float:right; margin-right:2em; border:1px solid gray; background:#eee; padding:1em"> 
  <g:formRemote name="myForm" action="remoteFormSubmit" update="myUpdateRegion" url="[action:'remoteFormSubmit']">
    <div>
      FormRemote
    </div>
    <div>
      <label for="firstName">First Name:</label>
      <g:textField name="firstName"/>
    </div>
    <div>
      <label for="lastName">Last Name:</label>
      <g:textField name="lastName"/>
    </div>
    <g:submitButton name="save" value="Save Data"/>
  </g:formRemote>
  <br/>

  <g:formRemote name="myForm2" action="remoteFormSubmit" update="myUpdateRegion" url="[action:'remoteFormSubmit']" method="Get" on200="onFailure200()">
    <div>
      FormRemote with Get
    </div>
    <div>
      <label for="firstName">First Name:</label>
      <g:textField name="firstName"/>
    </div>
    <div>
      <label for="lastName">Last Name:</label>
      <g:textField name="lastName"/>
    </div>
    <g:submitButton name="save" value="Save Data"/>
  </g:formRemote>
</div>


<div id="myUpdateRegion" style="width:600px; height:200px; border:1px solid gray; margin:2em"></div>
<div id="myUpdateRegion2" style="width:600px; height:200px; border:1px solid gray; margin:2em"></div>

</body>
</html>