job('job-practica'){
  description('Job para nuestra empresa')
  scm{
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git','main') { node ->
      node / gitConfigName('juanmaflo')
      node / gitConfigEmail('juanitocapotillo@gmail.com')
    }
  }
  parameters{
    stringParam('nombre',defaultValue='Julian',description='Parametro de cadena para el Job Booleano')
    choiceParam('planeta',['Mercurio','Venus','Tierra','Marte','Jupiter','Saturno','Urano','Neptuno'])
    booleanParam('agente',false)
  }
  triggers{
    cron('H/7 * * * *')
    githubPush()
  }
  steps{
    shell("bash jobscript.sh")  
  }
  publishers{
    mailer('juanmazonpracticasempresa@gmail.com',true,true)
    slackNotifier{
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
