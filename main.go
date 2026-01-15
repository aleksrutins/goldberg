package main

import (
	"context"
	"os"
	"os/exec"

	"farthergate.com/goldberg/config"
	"github.com/charmbracelet/lipgloss"
	"github.com/charmbracelet/log"
)

func main() {
	cfg, err := config.LoadFromPath(context.Background(), "goldberg.pkl")

	log.SetStyles(log.DefaultStyles())
	log.SetReportTimestamp(false)

	stepStyle := lipgloss.NewStyle().Bold(true).Foreground(lipgloss.Color("35"))
	cmnd := lipgloss.NewStyle().Foreground(lipgloss.Color("31"))

	if err != nil {
		panic(err)
	}

	for _, step := range cfg.Steps {
		log.Print(stepStyle.Render("STEP"), "name", step.Name)
		for _, command := range step.Commands {
			log.Print(cmnd.Render("EXEC"), "cmd", command)
			cmd := exec.Command(command[0], command[1:]...)
			cmd.Stdout = os.Stdout
			cmd.Stderr = os.Stderr
			cmd.Stdin = os.Stdin
			err := cmd.Run()
			if err != nil {
				log.Fatal(err)
			}
		}
	}
}
